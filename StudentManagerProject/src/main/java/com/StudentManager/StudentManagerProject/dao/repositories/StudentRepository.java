package com.StudentManager.StudentManagerProject.dao.repositories;

import com.StudentManager.StudentManagerProject.dao.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
//public Page<Student> findStudentByFirstNameContaining(String kw,PageRequest pageable);
    public Page<Student> findByFirstNameContainingOrLastNameContainingOrEmailContaining(String firstName , String LastName , String Email , Pageable pageable);
}
