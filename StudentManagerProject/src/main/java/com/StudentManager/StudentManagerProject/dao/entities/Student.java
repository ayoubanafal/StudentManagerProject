package com.StudentManager.StudentManagerProject.dao.entities;

import com.sun.jdi.LongValue;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "parents_number", columnDefinition = "VARCHAR(20) default '0000-0000-00'")
    private String parentsNumber="0000-0000-00";

    @Column(name = "first_semester_grade", columnDefinition = "bigint default 0")
    private Long firstSemesterGrade=0L;

    @Column(name = "second_semester_grade", columnDefinition = "bigint default 0")
    private Long secondSemesterGrade=0L;
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private Collection<Absence> absences;

    public Student() {}
    public Student(String firstName, String lastName, String email,String parentsNumber,Long firstSemesterGrade,Long secondSemesterGrade) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.parentsNumber=parentsNumber;
        this.firstSemesterGrade=firstSemesterGrade;
        this.secondSemesterGrade=secondSemesterGrade;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getParentsNumber() {return parentsNumber;}

    public void setParentsNumber(String parentsNumber) {this.parentsNumber = parentsNumber;}

    public Long getFirstSemesterGrade() {return firstSemesterGrade;}

    public void setFirstSemesterGrade(Long firstSemesterGrade) {this.firstSemesterGrade = firstSemesterGrade;}

    public Long getSecondSemesterGrade() {return secondSemesterGrade;}

    public void setSecondSemesterGrade(Long secondSemesterGrade) {this.secondSemesterGrade = secondSemesterGrade; }
}
