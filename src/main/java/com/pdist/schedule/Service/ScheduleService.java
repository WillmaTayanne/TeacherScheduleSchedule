package com.pdist.schedule.Service;

import com.pdist.schedule.ClientRPC;
import com.pdist.schedule.Model.Message;
import com.pdist.schedule.Model.Schedule;
import com.pdist.schedule.Model.Usuario;
import com.pdist.schedule.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> read() {
        return this.scheduleRepository.findAll();
    }

    public Schedule readById(Long id){
        return this.scheduleRepository.findById(id).orElse(null);
    }

    @Transactional
    public Schedule create(Schedule schedule){
        Schedule newSchedule = this.scheduleRepository.save(schedule);
        for(Usuario student : schedule.getStudents())
            sendMessage(newSchedule, student, "Novo Horário Cadastrado");

        return newSchedule;
    }

    @Transactional
    public Schedule update(Schedule schedule){
        Schedule newSchedule = this.scheduleRepository.save(schedule);
        for(Usuario student : schedule.getStudents())
            sendMessage(newSchedule, student, "Horário Atualizado");

        return newSchedule;
    }

    public static void sendMessage(Schedule schedule, Usuario student, String title) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Message message = new Message();

        message.setRead(false);
        message.setTitle(title);
        message.setUserIdOrigin(schedule.getTeacher());
        message.setUserIdDestination(student);
        message.setDateTime(new Timestamp(System.currentTimeMillis()));
        message.setDescription("Gostariamos de notificar que você possui uma aula em " +
                dateFormat.format(schedule.getDateTimeBegin()));
        ClientRPC.sendMessage(message);
    }

    public void delete(Long id){
        this.scheduleRepository.deleteById(id);
    }
}
