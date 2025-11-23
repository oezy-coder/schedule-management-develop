package com.example.schedulemanagementdevelop.schedule.repository;

import com.example.schedulemanagementdevelop.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
