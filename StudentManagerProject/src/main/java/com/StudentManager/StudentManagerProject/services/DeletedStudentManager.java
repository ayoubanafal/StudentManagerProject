package com.StudentManager.StudentManagerProject.services;

import com.StudentManager.StudentManagerProject.dao.entities.DeletedStudent;
import org.springframework.data.domain.Page;


import java.util.List;

public interface DeletedStudentManager {
    public List<DeletedStudent> getAllStudents();

    public DeletedStudent saveStudent(DeletedStudent deletedStudent);

    public DeletedStudent getStudentById(Long id);
    public DeletedStudent updateStudent(DeletedStudent deletedStudent);

    public Page<DeletedStudent> findDeletedStudentByFirstFirstNameOrLastNameOrEmailOrDeletionReason(String kw , int page , int size);
}
