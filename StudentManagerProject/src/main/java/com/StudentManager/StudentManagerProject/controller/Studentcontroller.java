package com.StudentManager.StudentManagerProject.controller;

import com.StudentManager.StudentManagerProject.dao.entities.Student;
import com.StudentManager.StudentManagerProject.services.StudentManager;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Studentcontroller {
    private StudentManager studentManager;
    public Studentcontroller (StudentManager studentManager)
    {
        super();
        this.studentManager=studentManager;
    }
//    @GetMapping("/students")
//    public String listStudents(Model model) {
//        model.addAttribute("students", studentManager.getAllStudents());
//        return "students";
//    }

    @GetMapping("/students")
    public String listStudents(Model model,
                               @RequestParam(name = "Search", defaultValue = "") String kw,
                               @RequestParam(name = "size", defaultValue = "2") int size,
                               @RequestParam(name = "page", defaultValue = "0") int page) {
        Page<Student> pageStudents = studentManager.findStudentByFirstName(kw, page, size);
        model.addAttribute("students",pageStudents.getContent());
        System.out.println("---------------------------");
        System.out.println(pageStudents.getTotalPages());
        System.out.println("---------------------------");
        model.addAttribute("pages",new int[pageStudents.getTotalPages()]);
        model.addAttribute("Tpages",pageStudents.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("Search",kw);
        return "students";
    }



    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";

    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentManager.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentManager.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student,
                                Model model) {
        Long a = Long.valueOf(0);
        Student existingStudent = studentManager.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        studentManager.updateStudent(existingStudent);
        return "redirect:/students";
    }


    @GetMapping("/students/info/{id}")
    public String infoStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentManager.getStudentById(id));
        return "info_student";
    }

    @PostMapping("/students1/{id}")
    public String updateStudentinfo(@PathVariable Long id,
                                @ModelAttribute("student") Student student,
                                Model model) {

        Student existingStudent = studentManager.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setParentsNumber(student.getParentsNumber());
        existingStudent.setFirstSemesterGrade(student.getFirstSemesterGrade());
        existingStudent.setSecondSemesterGrade(student.getSecondSemesterGrade());
        studentManager.updateStudent(existingStudent);
        return "redirect:/students";
    }


    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentManager.deleteStudentById(id);
        return "redirect:/students";
    }
    @GetMapping("/test")
    public String testStudent() {

        return "test";
    }
}
