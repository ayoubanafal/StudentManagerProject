package com.StudentManager.StudentManagerProject.dao.repositories;

import com.StudentManager.StudentManagerProject.dao.entities.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    public Page<Teacher> findTeacherByFirstNameContainingOrAndLastNameContainingOrEmailContaining(String firstName , String LastName , String Email , Pageable pageable);
}
