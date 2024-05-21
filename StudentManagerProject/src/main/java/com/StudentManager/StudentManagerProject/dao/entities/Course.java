package com.StudentManager.StudentManagerProject.dao.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long idC;
    private String name;
    private Long weeklyHrs;
    private String taughtBy;
    private int year;

    public Course() {
    }

    public Course(Long idC, String name, Long weeklyHrs, String taughtBy, int year) {
        this.idC = idC;
        this.name = name;
        this.weeklyHrs = weeklyHrs;
        this.taughtBy = taughtBy;
        this.year = year;
    }

    public Long getIdC() {
        return idC;
    }

    public void setIdC(Long idC) {
        this.idC = idC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWeeklyHrs() {
        return weeklyHrs;
    }

    public void setWeeklyHrs(Long weeklyHrs) {
        this.weeklyHrs = weeklyHrs;
    }

    public String getTaughtBy() {
        return taughtBy;
    }

    public void setTaughtBy(String taughtBy) {
        this.taughtBy = taughtBy;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
