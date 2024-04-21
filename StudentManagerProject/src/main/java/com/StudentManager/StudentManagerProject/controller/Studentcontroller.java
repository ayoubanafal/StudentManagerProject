package com.StudentManager.StudentManagerProject.controller;

import com.StudentManager.StudentManagerProject.dao.entities.DeletedStudent;
import com.StudentManager.StudentManagerProject.dao.entities.Student;
import com.StudentManager.StudentManagerProject.services.DeletedStudentManager;
import com.StudentManager.StudentManagerProject.services.StudentManager;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
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
///////listing
    @GetMapping("/students")
    public String listStudents(Model model,
                               @RequestParam(name = "Search", defaultValue = "") String kw,
                               @RequestParam(name = "size", defaultValue = "2") int size,
                               @RequestParam(name = "page", defaultValue = "0") int page) {
        Page<Student> pageStudents = studentManager.findStudentByFirstNameOrLastNameOrEmail(kw, page, size);
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
////new
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
//////updating
    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentManager.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
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
////////////delete
    @GetMapping("/students/delete/{id}")
    public String deleteStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentManager.getStudentById(id));
        return "delete_student";
    }
    @PostMapping("/studentsD/{id}")
    public String DStudent(@PathVariable Long id,
                                @ModelAttribute("student") DeletedStudent Dstudent,
                           @RequestParam(name ="deletionReason") String kw,
                                Model model) {
        Dstudent.setDeletionReason(kw);
        deletedStudentManager.saveStudent(Dstudent);
        studentManager.deleteStudentById(id);
        return "redirect:/students";
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
