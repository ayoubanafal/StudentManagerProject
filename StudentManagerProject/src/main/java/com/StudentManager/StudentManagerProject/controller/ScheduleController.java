package com.StudentManager.StudentManagerProject.controller;

import com.StudentManager.StudentManagerProject.dao.entities.Absence;
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
public class ScheduleController {
    private ScheduleManager scheduleManager;
    private TeacherManager teacherManager;

    public ScheduleController(ScheduleManager scheduleManager, TeacherManager teacherManager) {
        super();
        this.scheduleManager = scheduleManager;
        this.teacherManager = teacherManager;
    }
    @GetMapping("/schedules/{id}")
    public String listSchedule(Model model,
                               @PathVariable Long id,
                               @RequestParam(name = "size", defaultValue = "6") int size,
                               @RequestParam(name = "page", defaultValue = "0") int page) {
        Page<Schedule> pageTeachers = scheduleManager.findScheduleByTeacher(id,page,size);
        model.addAttribute("schedules",pageTeachers.getContent());
        return "schedules";
    }
    ///update
    @GetMapping("/schedulesEdit/{id}")
    public String editScheduleForm(@PathVariable Long id, Model model) {
        model.addAttribute("schedule", scheduleManager.getScheduleById(id));
        return "edit_schedule";
    }
    @PostMapping("/schedules/edit/{id}")
    public String updateSchedule(@PathVariable Long id,
                                @ModelAttribute("schedule") Schedule schedule) {

        Schedule existingSchedule = scheduleManager.getScheduleById(id);
        existingSchedule.setFirstPeriod(schedule.getFirstPeriod());
        existingSchedule.setSecondPeriod(schedule.getSecondPeriod());
        existingSchedule.setThirdPeriod(schedule.getThirdPeriod());
        existingSchedule.setFourthPeriod(schedule.getFourthPeriod());
        scheduleManager.updateSchedule(existingSchedule);
        return "redirect:/schedules/"+ existingSchedule.getTeacher().getIdT();
    }



    //////export
    @GetMapping("/ScheduleExportCSV/{id}")
    public void exportCSVSchedule(HttpServletResponse response,@PathVariable Long id) throws IOException {
        String fileName = "teacher-schedule-list.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

        try (CSVWriter csvWriter = new CSVWriter(response.getWriter())) {
            String[] header = { "Day", "First Period", "Second Period","Third Period","Fourth Period"};
            csvWriter.writeNext(header);
            List<Schedule> schedules = scheduleManager.getAllSchedules();
            for (Schedule schedule : schedules) {
                if (schedule.getTeacher().getIdT() == id) {
                    String[] data = {
                            schedule.getDay(),
                            schedule.getFirstPeriod(),
                            schedule.getSecondPeriod(),
                            schedule.getThirdPeriod(),
                            schedule.getFourthPeriod()
                    };
                    csvWriter.writeNext(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
