package com.StudentManager.StudentManagerProject.services;

import com.StudentManager.StudentManagerProject.dao.entities.Schedule;
import com.StudentManager.StudentManagerProject.dao.repositories.ScheduleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService implements ScheduleManager {
    private ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        super();
        this.scheduleRepository = scheduleRepository;
    }
    @Override
    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }
    @Override
    public Schedule saveSchedule(Schedule schedule){
        return scheduleRepository.save(schedule);
    }
    @Override
    public Schedule getScheduleById(Long id){
        return scheduleRepository.findById(id).get();
    }
    @Override
    public Schedule updateSchedule(Schedule schedule)
    {
        return scheduleRepository.save(schedule);
    }
    @Override
    public void deleteScheduleById(Long id){
        scheduleRepository.deleteById(id);
    }
    @Override
    public Page<Schedule> findScheduleByTeacher(Long kw , int size, int page)
    {
        return scheduleRepository.findScheduleByTeacherIdT(kw, PageRequest.of(size,page));
    }
    @Override
    public List<Schedule> findScheduleByTeacher(Long kw)
    {
        return scheduleRepository.findScheduleByTeacherIdT(kw);
    }
}
