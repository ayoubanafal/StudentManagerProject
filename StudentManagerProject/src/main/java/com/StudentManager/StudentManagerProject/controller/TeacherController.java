package com.StudentManager.StudentManagerProject.controller;

import com.StudentManager.StudentManagerProject.dao.entities.Schedule;
import com.StudentManager.StudentManagerProject.dao.entities.Teacher;
import com.StudentManager.StudentManagerProject.services.ScheduleManager;
import com.StudentManager.StudentManagerProject.services.TeacherManager;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherController {
    private TeacherManager teacherManager;
    private ScheduleManager scheduleManager;

    public TeacherController(TeacherManager teacherManager, ScheduleManager scheduleManager) {
        super();
        this.teacherManager = teacherManager;
        this.scheduleManager = scheduleManager;
    }
    @GetMapping("/teachers")
    public String listTeachers(Model model,
                               @RequestParam(name = "Search", defaultValue = "") String kw,
                               @RequestParam(name = "size", defaultValue = "2") int size,
                               @RequestParam(name = "page", defaultValue = "0") int page) {
        Teacher teacher = new Teacher();
        model.addAttribute("teacher",teacher);
        Page<Teacher> pageTeachers = teacherManager.findTeacherByFirstNameOrLastNameOrEmail(kw,page,size);
        model.addAttribute("teachers",pageTeachers.getContent());
        System.out.println(pageTeachers.getTotalPages());
        model.addAttribute("pages",new int[pageTeachers.getTotalPages()]);
        model.addAttribute("Tpages",pageTeachers.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("Search",kw);
        return "teachers";
    }
    ////new
    @GetMapping("/teachers/new")
    public String createTeacherForm(Model model) {
        List<Teacher> teachers = teacherManager.getAllTeachers();
        model.addAttribute("teachers",teachers);
        Teacher teacher = new Teacher();
        model.addAttribute("teacher",teacher);
        return "teachers";
    }

    @PostMapping("/teachersn")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
        List<String> days = new ArrayList<>();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        teacherManager.saveTeacher(teacher);
        for(int i=0;i<6;i++){
            Schedule schedule=new Schedule();
            schedule.setDay(days.get(i));
            schedule.setTeacher(teacher);
            scheduleManager.saveSchedule(schedule);
        }
        return "redirect:/teachers";
    }
    //////updating
    @GetMapping("/teachersEdit/{id}")
    public String editTeacherForm(@PathVariable Long id, Model model) {
        model.addAttribute("teacher", teacherManager.getTeacherById(id));
        return "edit_teacher";
    }

    @PostMapping("/teachers/{id}")
    public String updateTeacher(@PathVariable Long id,
                                @ModelAttribute("teacher") Teacher teacher) {

        Teacher existingTeacher = teacherManager.getTeacherById(id);
        existingTeacher.setIdT(id);
        existingTeacher.setFirstName(teacher.getFirstName());
        existingTeacher.setLastName(teacher.getLastName());
        existingTeacher.setEmail(teacher.getEmail());
        existingTeacher.setPhoneNumber(teacher.getPhoneNumber());
        teacherManager.saveTeacher(existingTeacher);
        return "redirect:/teachers";
    }
    ////////////delete
    @GetMapping("/teachers/delete/{id}")
    public String deleteTeacherForm(@PathVariable Long id) {
        // i need to delete the schedule with the teacher
        List<Schedule> schedules=scheduleManager.findScheduleByTeacher(id);
        for (Schedule schedule : schedules) {
            scheduleManager.deleteScheduleById(schedule.getIdS());
        }
        teacherManager.deleteTeacherById(id);
        return "redirect:/teachers";
    }

    ////export
    @GetMapping("/exportCSVT")
    public void exportCSV(HttpServletResponse response) throws IOException {
        String fileName = "teacher-list.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

        try (CSVWriter csvWriter = new CSVWriter(response.getWriter())) {

            String[] header = { "Id", "First Name", "Last Name", "Email", "Phone Number"};
            csvWriter.writeNext(header);


            List<Teacher> teachers = teacherManager.getAllTeachers();


            for (Teacher teacher : teachers) {
                String[] data = {
                        String.valueOf(teacher.getIdT()),
                        teacher.getFirstName(),
                        teacher.getLastName(),
                        teacher.getEmail(),
                        teacher.getPhoneNumber()
                };
                csvWriter.writeNext(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
