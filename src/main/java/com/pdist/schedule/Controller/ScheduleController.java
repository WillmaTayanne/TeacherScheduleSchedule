package com.pdist.schedule.Controller;

import com.pdist.schedule.DTO.ScheduleRequest;
import com.pdist.schedule.Model.Schedule;
import com.pdist.schedule.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/schedule")
    public List<Schedule> read(){
        return this.scheduleService.read();
    }

    @GetMapping("/schedule/{id}")
    public Schedule readById(@PathVariable("id") Long id){
        return this.scheduleService.readById(id);
    }

    @PostMapping("/schedule")
    public Schedule create(@RequestBody ScheduleRequest scheduleRequest){
        return this.scheduleService.create(scheduleRequest);
    }

    @PutMapping("/schedule/{id}")
    public Schedule update(@RequestBody Schedule schedule){
        return this.scheduleService.update(schedule);
    }

    @DeleteMapping("/schedule/{id}")
    public void delete(@PathVariable("id") Long id){
        this.scheduleService.delete(id);
    }
}
