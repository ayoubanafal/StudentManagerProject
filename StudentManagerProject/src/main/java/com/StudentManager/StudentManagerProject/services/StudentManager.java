package com.StudentManager.StudentManagerProject.services;

import com.StudentManager.StudentManagerProject.dao.entities.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentManager {
    public List<Student> getAllStudents();

    public Student saveStudent(Student student);

    public Student getStudentById(Long id);

    public Student updateStudent(Student student);

    public void deleteStudentById(Long id);
    //Page<Student> findStudentByFirstName(String kw , int page , int size);
    public  Page<Student> findStudentByFirstNameOrLastNameOrEmail(String kw , int page , int size);
}
