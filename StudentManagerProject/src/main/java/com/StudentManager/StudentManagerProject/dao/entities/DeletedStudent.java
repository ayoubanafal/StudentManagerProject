package com.StudentManager.StudentManagerProject.dao.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "DeletedStudents")
public class DeletedStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
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
    private String deletionReason;


    public DeletedStudent() {}
    public DeletedStudent(String firstName, String lastName, String email,String parentsNumber,Long firstSemesterGrade,Long secondSemesterGrade,String deletionReason) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.parentsNumber=parentsNumber;
        this.firstSemesterGrade=firstSemesterGrade;
        this.secondSemesterGrade=secondSemesterGrade;
        this.deletionReason=deletionReason;
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
    public String getDeletionReason() { return deletionReason; }
    public void setDeletionReason(String deletionReason) { this.deletionReason = deletionReason; }


}