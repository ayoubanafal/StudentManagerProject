package com.StudentManager.StudentManagerProject.services;

import com.StudentManager.StudentManagerProject.dao.entities.Student;
import com.StudentManager.StudentManagerProject.dao.repositories.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService implements StudentManager {
    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        super();
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Page<Student> findStudentByFirstName(String kw , int size , int page)
    {
        return studentRepository.findStudentByFirstNameContaining(kw,PageRequest.of(size,page));
    }

}
