package com.StudentManager.StudentManagerProject.services;

import com.StudentManager.StudentManagerProject.dao.entities.Schedule;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ScheduleManager {
    public List<Schedule> getAllSchedules();

    public Schedule saveSchedule(Schedule schedule);

    public Schedule getScheduleById(Long id);

    public Schedule updateSchedule(Schedule schedule);

    public void deleteScheduleById(Long id);

    public List<Schedule> findScheduleByTeacher(Long kw);
    public Page<Schedule> findScheduleByTeacher(Long kw , int page , int size);
}
