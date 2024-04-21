package com.StudentManager.StudentManagerProject.controller;

import com.StudentManager.StudentManagerProject.dao.entities.DeletedStudent;
import com.StudentManager.StudentManagerProject.dao.entities.Student;
import com.StudentManager.StudentManagerProject.services.DeletedStudentManager;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class DeletedStudentController {
    private DeletedStudentManager deletedStudentManager;

    public DeletedStudentController(DeletedStudentManager deletedStudentManager) {
        super();
        this.deletedStudentManager = deletedStudentManager;
    }
    @GetMapping("/deletedStudents")
    public String listStudents(Model model,
                               @RequestParam(name = "Search", defaultValue = "") String kw,
                               @RequestParam(name = "size", defaultValue = "2") int size,
                               @RequestParam(name = "page", defaultValue = "0") int page) {
        Page<DeletedStudent> pageStudents = deletedStudentManager.findDeletedStudentByFirstFirstNameOrLastNameOrEmailOrDeletionReason(kw, page, size);
        model.addAttribute("students",pageStudents.getContent());
        System.out.println("---------------------------");
        System.out.println(pageStudents.getTotalPages());
        System.out.println("---------------------------");
        model.addAttribute("pages",new int[pageStudents.getTotalPages()]);
        model.addAttribute("Tpages",pageStudents.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("Search",kw);
        return "deleted-students";
    }

    @GetMapping("/dexportCSV")
    public void exportCSV(HttpServletResponse response) throws IOException {
        String fileName = "deletedStudent-list.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

        try (CSVWriter csvWriter = new CSVWriter(response.getWriter())) {
            String[] header = { "Id", "First Name", "Last Name", "Email", "Parents Number", "First Semester Grade", "Second Semester Grade","Deletion Reason" };
            csvWriter.writeNext(header);

            List<DeletedStudent> deletedStudents = deletedStudentManager.getAllStudents();

            for (DeletedStudent deletedStudent : deletedStudents) {
                String[] data = {
                        String.valueOf(deletedStudent.getId()),
                        deletedStudent.getFirstName(),
                        deletedStudent.getLastName(),
                        deletedStudent.getEmail(),
                        deletedStudent.getParentsNumber(),
                        String.valueOf(deletedStudent.getFirstSemesterGrade()),
                        String.valueOf(deletedStudent.getSecondSemesterGrade()),
                        deletedStudent.getDeletionReason()
                };
                csvWriter.writeNext(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
