package com.StudentManager.StudentManagerProject.services;

import com.StudentManager.StudentManagerProject.dao.entities.Admin;
import com.StudentManager.StudentManagerProject.dao.repositories.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private AdminRepository adminRepository;
    public AdminService(AdminRepository adminRepository)
    {
        this.adminRepository=adminRepository;
    }
    public Admin registerAdmin(String userName, String password, String email)
    {
        if (userName !=null && password !=null)
        {
            return null;
        }
        else {
            Admin admin= new Admin();
            admin.setUserName(userName);
            admin.setEmail(email);
            admin.setPassword(password);
            return adminRepository.save(admin);
        }
    }
    public Admin authenticate(String username, String password)
    {
        return adminRepository.findByUserNameAndPassword(username, password).orElse(null);
    }
}
