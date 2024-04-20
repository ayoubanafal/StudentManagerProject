package com.StudentManager.StudentManagerProject.dao.repositories;

import com.StudentManager.StudentManagerProject.dao.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
// already has default crud methods
    Optional<Admin> findByUserNameAndPassword(String userName , String password);
}
