package com.StudentManager.StudentManagerProject.controller;

import com.StudentManager.StudentManagerProject.dao.entities.Absence;
import com.StudentManager.StudentManagerProject.dao.entities.Course;
import com.StudentManager.StudentManagerProject.dao.entities.Student;
import com.StudentManager.StudentManagerProject.dao.entities.Teacher;
import com.StudentManager.StudentManagerProject.services.CourseManager;
import com.StudentManager.StudentManagerProject.services.TeacherManager;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Controller
public class CourseController {
    private CourseManager courseManager;
    private TeacherManager teacherManager;

    public CourseController(CourseManager courseManager, TeacherManager teacherManager) {
        this.courseManager = courseManager;
        this.teacherManager = teacherManager;
    }
    @GetMapping("/course")
    public String listCourse(Model model) {

        //model.addAttribute("courses",pageCourses.getContent());// search dosent work
        List<Course> courses = courseManager.getAllCourses();
        courses.sort(Comparator.comparing(Course::getName));
        model.addAttribute("courses",courses);
        return "course";
    }
    ////////////////////new
    @GetMapping("/courseNewG")
    public String newCourseG(Model model) {
        Course course = new Course();
        List<Teacher> teachers = teacherManager.getAllTeachers();
        model.addAttribute("teachers", teachers);
        model.addAttribute("course", course);
        return "create_course";
    }
    @PostMapping("/courseNewP")//2
    public String newCourseP(@ModelAttribute("course") Course course) {
        courseManager.saveCourse(course);
        return "redirect:/course";
    }
    /////////////////////update
    @GetMapping("/courseUpdateG/{id}")
    public String editCourseG(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseManager.getCourseById(id));
        model.addAttribute("teachers", teacherManager.getAllTeachers());
        return "edit_course";
    }
    @PostMapping("/courseUpdateG/{id}")
    public String editCourseP(@PathVariable Long id,
                                @ModelAttribute("course") Course course) {

        Course existingCourse = courseManager.getCourseById(id);
        existingCourse.setIdC(id);
        existingCourse.setName(course.getName());
        existingCourse.setTaughtBy(course.getTaughtBy());
        existingCourse.setWeeklyHrs(course.getWeeklyHrs());
        existingCourse.setYear(course.getYear());
        courseManager.updateCourse(existingCourse);
        return "redirect:/course";
    }
    ///////////////delete
    @GetMapping("/courseDelete/{id}")
    public String DAbsence1(@PathVariable Long id) {
        courseManager.deleteCourseById(id);
        return "redirect:/course";
    }
    /////export
    @GetMapping("/CourseExportCSV")
    public void exportCSVAbsence(HttpServletResponse response) throws IOException {
        String fileName = "Courses-list.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

        try (CSVWriter csvWriter = new CSVWriter(response.getWriter())) {
            // Define the CSV header
            String[] header = { "Id", "Name", "Taught By","Weekly Hours"};
            csvWriter.writeNext(header);

            // Get all students from the database
            List<Course> courses = courseManager.getAllCourses();

            // Write each student's data to the CSV file
            for (Course course : courses) {
                String[] data = {
                        String.valueOf(course.getIdC()),
                        course.getName(),
                        course.getTaughtBy(),
                        String.valueOf(course.getWeeklyHrs())
                };
                csvWriter.writeNext(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
