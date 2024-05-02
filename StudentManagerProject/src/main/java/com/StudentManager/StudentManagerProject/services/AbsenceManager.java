package com.StudentManager.StudentManagerProject.services;

import com.StudentManager.StudentManagerProject.dao.entities.Absence;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AbsenceManager {
    public List<Absence> getAllAbsences();

    public Absence saveAbsences(Absence absence);

    public Absence getAbsenceById(Long id);

    public Absence updateAbsence(Absence absence);

    public void deleteAbsenceById(Long id);

    public Page<Absence> findAbsenceByStudent(Long kw , int page , int size);
}
