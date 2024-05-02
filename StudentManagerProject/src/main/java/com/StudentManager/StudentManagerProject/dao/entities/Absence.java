package com.StudentManager.StudentManagerProject.dao.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "absences")
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String reason;
    private String missedClass;
    @ManyToOne
    private Student student;

    public Absence() {
    }

    public Absence(Long id, Date date, String reason, Student student,String missedClass) {
        this.id = id;
        this.date = date;
        this.reason = reason;
        this.student = student;
        this.missedClass=missedClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getMissedClass() {
        return missedClass;
    }

    public void setMissedClass(String missedClass) {
        this.missedClass = missedClass;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
