package com.StudentManager.StudentManagerProject.controller;

import com.StudentManager.StudentManagerProject.dao.entities.Teacher;
import com.StudentManager.StudentManagerProject.dao.entities.User;
import com.StudentManager.StudentManagerProject.dto.UserDto;
import com.StudentManager.StudentManagerProject.services.UserManager;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Controller
public class AuthController {
    private UserManager userManager;
    private final JdbcTemplate jdbcTemplate;

    public AuthController(UserManager userManager, JdbcTemplate jdbcTemplate) {
        this.userManager = userManager;
        this.jdbcTemplate = jdbcTemplate;
    }
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userManager.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }
        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }
        userManager.saveUser(userDto);
        return "redirect:/register?success";
    }


    @GetMapping("/users")
    public String users(Model model,
                        @RequestParam(name = "Search", defaultValue = "") String kw,
                        @RequestParam(name = "size", defaultValue = "8") int size,
                        @RequestParam(name = "page", defaultValue = "0") int page) {
        Page<User> pageUsers = userManager.findUserByEmail(kw,page,size);
        //model.addAttribute("users",pageUsers.getContent());
        model.addAttribute("pages",new int[pageUsers.getTotalPages()]);
        model.addAttribute("Tpages",pageUsers.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("Search",kw);
        List<UserDto> users = userManager.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/changeRole1/{email}")
    public String changeRoles1(@PathVariable String email) {
        User user = userManager.findUserByEmail(email);
        if (user != null) {
            Long id = user.getId();
            String sql = "UPDATE users_roles SET role_id = '1' WHERE user_id = ?";//super
            jdbcTemplate.update(sql, id);
        }
        return "redirect:/users";
    }

    @GetMapping("/changeRole2/{email}")
    public String changeRoles2(@PathVariable String email) {
        User user = userManager.findUserByEmail(email);
        if (user != null) {
            Long id = user.getId();
            String sql = "UPDATE users_roles SET role_id = '5' WHERE user_id = ?";//admin
            jdbcTemplate.update(sql, id);
        }
        return "redirect:/users";
    }
    @GetMapping("/changeRole3/{email}")
    public String changeRoles3(@PathVariable String email) {
        User user = userManager.findUserByEmail(email);
        if (user != null) {
            Long id = user.getId();
            String sql = "UPDATE users_roles SET role_id = '6' WHERE user_id = ?";//user
            jdbcTemplate.update(sql, id);
        }
        return "redirect:/users";
    }

}
