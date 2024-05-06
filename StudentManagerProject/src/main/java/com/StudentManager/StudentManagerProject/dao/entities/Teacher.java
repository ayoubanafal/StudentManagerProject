package com.StudentManager.StudentManagerProject.dao.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idT;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
    private Collection<Schedule> schedules;
    public Teacher() {
    }

    public Teacher(Long idT, String firstName, String lastName, String email, String phoneNumber, Collection<Schedule> schedules) {
        super();
        this.idT = idT;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.schedules = schedules;
    }

    public Long getIdT() {
        return idT;
    }

    public void setIdT(Long idT) {
        this.idT = idT;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Collection<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Collection<Schedule> schedules) {
        this.schedules = schedules;
    }
}
