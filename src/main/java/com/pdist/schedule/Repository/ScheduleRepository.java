package com.pdist.schedule.Repository;

import com.pdist.schedule.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
