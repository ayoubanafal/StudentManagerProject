package com.StudentManager.StudentManagerProject.services;

import com.StudentManager.StudentManagerProject.dao.entities.Course;
import com.StudentManager.StudentManagerProject.dao.entities.Schedule;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseManager {
    public List<Course> getAllCourses();

    public Course saveCourse(Course course);

    public Course getCourseById(Long id);

    public Course updateCourse(Course course);

    public void deleteCourseById(Long id);
    public Page<Course> findCourseByNameOrTeacher(String kw , int page,int size);
    public Page<Course> findCourseByName(String kw , int page,int size);
}
