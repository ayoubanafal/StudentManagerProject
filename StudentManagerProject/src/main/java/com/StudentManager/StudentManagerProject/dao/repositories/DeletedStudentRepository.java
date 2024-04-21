package com.StudentManager.StudentManagerProject.dao.repositories;

import com.StudentManager.StudentManagerProject.dao.entities.DeletedStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletedStudentRepository extends JpaRepository<DeletedStudent,Long> {

    public Page<DeletedStudent> findByFirstNameContainingOrLastNameContainingOrEmailContainingOrDeletionReasonContaining(String firstName , String LastName , String Email , String DeletionReason ,Pageable pageable);
}
