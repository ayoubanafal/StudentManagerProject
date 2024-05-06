package com.StudentManager.StudentManagerProject.dao.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idS;
    private String day;
    private String firstPeriod;
    private String secondPeriod;
    private String thirdPeriod;
    private String fourthPeriod;
    @ManyToOne
    private Teacher teacher;

    public Schedule() {
    }

    public Schedule(String day,Long idS, String firstPeriod, String secondPeriod, String thirdPeriod, String fourthPeriod, Teacher teacher) {
        this.idS = idS;
        this.firstPeriod = firstPeriod;
        this.secondPeriod = secondPeriod;
        this.thirdPeriod = thirdPeriod;
        this.fourthPeriod = fourthPeriod;
        this.teacher = teacher;
        this.day=day;
    }

    public Long getIdS() {
        return idS;
    }

    public void setIdS(Long idS) {
        this.idS = idS;
    }

    public String getFirstPeriod() {
        return firstPeriod;
    }

    public void setFirstPeriod(String firstPeriod) {
        this.firstPeriod = firstPeriod;
    }
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSecondPeriod() {
        return secondPeriod;
    }

    public void setSecondPeriod(String secondPeriod) {
        this.secondPeriod = secondPeriod;
    }

    public String getThirdPeriod() {
        return thirdPeriod;
    }

    public void setThirdPeriod(String thirdPeriod) {
        this.thirdPeriod = thirdPeriod;
    }

    public String getFourthPeriod() {
        return fourthPeriod;
    }

    public void setFourthPeriod(String fourthPeriod) {
        this.fourthPeriod = fourthPeriod;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
