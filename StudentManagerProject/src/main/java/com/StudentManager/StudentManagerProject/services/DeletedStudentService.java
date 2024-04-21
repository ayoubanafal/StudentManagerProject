package com.StudentManager.StudentManagerProject.services;

import com.StudentManager.StudentManagerProject.dao.entities.DeletedStudent;
import com.StudentManager.StudentManagerProject.dao.repositories.DeletedStudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class DeletedStudentService implements DeletedStudentManager{
    private DeletedStudentRepository deletedStudentRepository;

    public DeletedStudentService(DeletedStudentRepository deletedStudentRepository) {
        super();
        this.deletedStudentRepository = deletedStudentRepository;
    }

    @Override
    public List<DeletedStudent> getAllStudents() {
        return deletedStudentRepository.findAll();
    }

    @Override
    public DeletedStudent saveStudent(DeletedStudent deletedStudent) {
        return deletedStudentRepository.save(deletedStudent);
    }

    @Override
    public DeletedStudent getStudentById(Long id) {
        return deletedStudentRepository.findById(id).get();
    }
    @Override
    public DeletedStudent updateStudent(DeletedStudent deletedStudent){
        return deletedStudentRepository.save(deletedStudent);
    }
    @Override
    public Page<DeletedStudent> findDeletedStudentByFirstFirstNameOrLastNameOrEmailOrDeletionReason(String kw , int size , int page)
    {
        return deletedStudentRepository.findByFirstNameContainingOrLastNameContainingOrEmailContainingOrDeletionReasonContaining(kw,kw,kw,kw, PageRequest.of(size,page));
    }
}
