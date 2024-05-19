package com.StudentManager.StudentManagerProject.controller;

import com.StudentManager.StudentManagerProject.dao.entities.Admin;
import com.StudentManager.StudentManagerProject.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest",new Admin());
        return "login_page";
    }
    @GetMapping("/error_page")
    public String getLoginPag(Model model) {
        return "error_page";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute Admin admin,Model model)
    {
        System.out.println("login request: "+ admin);
        Admin authenticated= adminService.authenticate(admin.getUserName(), admin.getPassword());
        if(authenticated != null)
        {
            model.addAttribute("userLogin",authenticated.getUserName());
            return "redirect:/students";
        }else {
            return "error_page";
        }
        }

}
