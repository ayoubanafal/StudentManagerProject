package com.StudentManager.StudentManagerProject.services;

import com.StudentManager.StudentManagerProject.dao.entities.Teacher;
import com.StudentManager.StudentManagerProject.dao.repositories.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements TeacherManager{
    private TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        super();
        this.teacherRepository = teacherRepository;
    }
    @Override
    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }
    @Override
    public Teacher saveTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }
    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).get();
    }
    @Override
    public Teacher updateTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }
@Override
    public void deleteTeacherById(Long id){
        teacherRepository.deleteById(id);
    }
    @Override
    public Page<Teacher> findTeacherByFirstNameOrLastNameOrEmail(String kw , int page , int size){
        return teacherRepository.findTeacherByFirstNameContainingOrAndLastNameContainingOrEmailContaining(kw,kw,kw,PageRequest.of(page,size));
    }
}
