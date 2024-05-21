package com.StudentManager.StudentManagerProject.services;

import com.StudentManager.StudentManagerProject.dao.entities.Course;
import com.StudentManager.StudentManagerProject.dao.repositories.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements CourseManager{
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        super();
        this.courseRepository = courseRepository;
    }@Override

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }
    @Override
    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }
    @Override
    public Course getCourseById(Long id){
        return courseRepository.findById(id).get();
    }
    @Override
    public Course updateCourse(Course course){
        return  courseRepository.save(course);
    }
    @Override
    public void deleteCourseById(Long id){
        courseRepository.deleteById(id);
    }
    @Override
    public Page<Course> findCourseByNameOrTeacher(String kw , int page, int size){
        return courseRepository.findCourseByNameContainingOrAndTaughtByContaining(kw,kw, PageRequest.of(size,page));
    }
    @Override
    public Page<Course> findCourseByName(String kw , int page, int size){
        return courseRepository.findCourseByNameContaining(kw, PageRequest.of(size,page));
    }
}
