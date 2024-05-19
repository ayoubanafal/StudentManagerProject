package com.StudentManager.StudentManagerProject.dao.repositories;

import com.StudentManager.StudentManagerProject.dao.entities.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    Page<Schedule> findScheduleByTeacherIdT(Long id, Pageable pageable);
    List<Schedule> findScheduleByTeacherIdT(Long id);
}
