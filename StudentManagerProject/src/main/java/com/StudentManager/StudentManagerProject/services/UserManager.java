package com.StudentManager.StudentManagerProject.services;

import com.StudentManager.StudentManagerProject.dao.entities.User;
import com.StudentManager.StudentManagerProject.dto.UserDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserManager {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
    Optional<User> findUserByID(Long id);

    List<UserDto> findAllUsers();
    public Page<User> findUserByEmail(String kw , int page , int size);
}
