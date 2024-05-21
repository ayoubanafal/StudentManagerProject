package com.StudentManager.StudentManagerProject.dao.repositories;

import com.StudentManager.StudentManagerProject.dao.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Page<Course> findCourseByNameContainingOrAndTaughtByContaining(String name, String taughtBy, Pageable pageable);
Page<Course> findCourseByNameContaining(String name , Pageable pageable);
}
