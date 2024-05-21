package com.StudentManager.StudentManagerProject.controller;

import com.StudentManager.StudentManagerProject.dao.entities.DeletedStudent;
import com.StudentManager.StudentManagerProject.dao.entities.Student;
import com.StudentManager.StudentManagerProject.services.DeletedStudentManager;
import com.StudentManager.StudentManagerProject.services.StudentManager;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class Studentcontroller {
    private StudentManager studentManager;
    private DeletedStudentManager deletedStudentManager;
    public Studentcontroller (StudentManager studentManager, DeletedStudentManager deletedStudentManager)
    {
        super();
        this.studentManager=studentManager;
        this.deletedStudentManager = deletedStudentManager;
    }

    @GetMapping("/test")//2
    public String listTest(Model model,
                           @RequestParam(name = "Search", defaultValue = "") String kw,
                           @RequestParam(name = "size", defaultValue = "4") int size,
                           @RequestParam(name = "page", defaultValue = "0") int page) {
        //List<Student> students = studentManager.getAllStudents();
        //model.addAttribute("students", students);
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("studentEdit", student);

        Page<Student> pageStudents = studentManager.findStudentByFirstNameOrLastNameOrEmail(kw, page, size);
        model.addAttribute("students",pageStudents.getContent());
        System.out.println("---------------------------");
        System.out.println(pageStudents.getTotalPages());
        System.out.println("---------------------------");
        model.addAttribute("pages",new int[pageStudents.getTotalPages()]);
        model.addAttribute("Tpages",pageStudents.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("Search",kw);
        return "test";
    }

////new
    @GetMapping("/students22")//2
    public String createStudentForm(Model model) {
        List<Student> students = studentManager.getAllStudents();
        model.addAttribute("students", students);
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("studentEdit", student);
        return "test";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentManager.saveStudent(student);
        return "redirect:/students";
    }
    @PostMapping("/students2")//2
    public String saveStudents(@ModelAttribute("student") Student student) {
        studentManager.saveStudent(student);
        return "redirect:/test";
    }
//////updating
    @GetMapping("/studentsEdit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentManager.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student) {

        Student existingStudent = studentManager.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setParentsNumber(student.getParentsNumber());
        existingStudent.setFirstSemesterGrade(student.getFirstSemesterGrade());
        existingStudent.setSecondSemesterGrade(student.getSecondSemesterGrade());
        studentManager.updateStudent(existingStudent);
        return "redirect:/test";
    }
////////////delete
    @GetMapping("/studentsDelete/{id}")
    public String deleteStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentManager.getStudentById(id));
        return "deleteStudent";
    }
    @PostMapping("/studentsD/{id}")
    public String DStudent(@PathVariable Long id,
                                @ModelAttribute("student") DeletedStudent Dstudent,
                           @RequestParam(name ="deletionReason") String kw,
                                Model model) {
        Dstudent.setDeletionReason(kw);
        deletedStudentManager.saveStudent(Dstudent);
        studentManager.deleteStudentById(id);
        return "redirect:/test";
    }

    ////export
@GetMapping("/exportCSV")
public void exportCSV(HttpServletResponse response) throws IOException {
    String fileName = "student-list.csv";

    response.setContentType("text/csv");
    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

    try (CSVWriter csvWriter = new CSVWriter(response.getWriter())) {
        // Define the CSV header
        String[] header = { "Id", "First Name", "Last Name", "Email", "Parents Number", "First Semester Grade", "Second Semester Grade" };
        csvWriter.writeNext(header);

        // Get all students from the database
        List<Student> students = studentManager.getAllStudents();

        // Write each student's data to the CSV file
        for (Student student : students) {
            String[] data = {
                    String.valueOf(student.getId()),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getEmail(),
                    student.getParentsNumber(),
                    String.valueOf(student.getFirstSemesterGrade()),
                    String.valueOf(student.getSecondSemesterGrade())
            };
            csvWriter.writeNext(data);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
