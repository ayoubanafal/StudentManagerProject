package com.StudentManager.StudentManagerProject.services;

import com.StudentManager.StudentManagerProject.dao.entities.Teacher;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeacherManager {
    public List<Teacher> getAllTeachers();

    public Teacher saveTeacher(Teacher teacher);

    public Teacher getTeacherById(Long id);

    public Teacher updateTeacher(Teacher teacher);

    public void deleteTeacherById(Long id);
    public Page<Teacher> findTeacherByFirstNameOrLastNameOrEmail(String kw , int page , int size);
}
