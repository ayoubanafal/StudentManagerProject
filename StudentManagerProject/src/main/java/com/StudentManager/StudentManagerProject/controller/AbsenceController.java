package com.StudentManager.StudentManagerProject.controller;

import com.StudentManager.StudentManagerProject.dao.entities.Absence;
import com.StudentManager.StudentManagerProject.dao.entities.Student;
import com.StudentManager.StudentManagerProject.services.AbsenceManager;
import com.StudentManager.StudentManagerProject.services.StudentManager;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AbsenceController {
    private AbsenceManager absenceManager;
    private StudentManager studentManager;

    public AbsenceController(AbsenceManager absenceManager, StudentManager studentManager) {
        super();
        this.absenceManager = absenceManager;
        this.studentManager = studentManager;
    }

    @GetMapping("/studentsAbsence/{id}")
    public String listStudentsAbsence(Model model,
                                      @PathVariable Long id,
                                      @RequestParam(name = "Search", defaultValue = "") String kw,
                                      @RequestParam(name = "size", defaultValue = "2") int size,
                                      @RequestParam(name = "page", defaultValue = "0") int page) {
        Page<Absence> pageAbsences = absenceManager.findAbsenceByStudent(id, page, size);
        model.addAttribute("Absences", pageAbsences.getContent());
        System.out.println(pageAbsences.getTotalPages());
        model.addAttribute("pages", new int[pageAbsences.getTotalPages()]);
        model.addAttribute("Tpages", pageAbsences.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("id", id);
        model.addAttribute("Search",kw);
        return "studentsAbsence";
    }

    ///////////new
    @GetMapping("/studentsAbsence/new/{id}")
    public String StudentAbsenceForm(Model model, @PathVariable Long id) {
        Absence absence = new Absence();
        model.addAttribute("absence", absence);
        return "create_absence";
    }
    @PostMapping("/studentsAbsence/{id}")
    public String saveStudentAbsence(@ModelAttribute("absence") Absence absence,@PathVariable Long id){
        Absence abs = new Absence();
        abs.setDate(absence.getDate());
        abs.setReason(absence.getReason());
        abs.setMissedClass(absence.getMissedClass());
        abs.setStudent(studentManager.getStudentById(id));

        absenceManager.saveAbsences(abs);
        return "redirect:/studentsAbsence/"+ id;
    }
    ////update

    @GetMapping("/studentsAbsence/edit/{id}")
    public String editAbsenceForm(@PathVariable Long id, Model model) {
        model.addAttribute("absence", absenceManager.getAbsenceById(id));
        return "edit_absence";
    }
    @PostMapping("/studentsAbsence/edit/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("absence") Absence absence) {

        Absence existingAbsence = absenceManager.getAbsenceById(id);
        existingAbsence.setId(id);
        existingAbsence.setMissedClass(absence.getMissedClass());
        existingAbsence.setReason(absence.getReason());
        existingAbsence.setDate(absence.getDate());
        absenceManager.updateAbsence(existingAbsence);
        return "redirect:/studentsAbsence/"+ id;
    }

    //////export
    @GetMapping("/AbsenceExportCSV")
    public void exportCSVAbsence(HttpServletResponse response) throws IOException {
        String fileName = "student-absence-list.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

        try (CSVWriter csvWriter = new CSVWriter(response.getWriter())) {
            // Define the CSV header
            String[] header = { "Id", "Date", "Missed Class","Reason"};
            csvWriter.writeNext(header);

            // Get all students from the database
            List<Absence> absences = absenceManager.getAllAbsences();

            // Write each student's data to the CSV file
            for (Absence absence : absences) {
                String[] data = {
                        String.valueOf(absence.getId()),
                        String.valueOf(absence.getDate()),
                        absence.getMissedClass(),
                        absence.getReason()
                };
                csvWriter.writeNext(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
