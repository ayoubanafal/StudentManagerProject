package com.StudentManager.StudentManagerProject.services;

import com.StudentManager.StudentManagerProject.dao.entities.Absence;
import com.StudentManager.StudentManagerProject.dao.repositories.AbsenceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AbsenceService implements AbsenceManager{
    private AbsenceRepository absenceRepository;

    public AbsenceService(AbsenceRepository absenceRepository) {
        this.absenceRepository = absenceRepository;
    }

    @Override
    public List<Absence> getAllAbsences() {
        return absenceRepository.findAll();
    }
    @Override
    public Absence saveAbsences(Absence absence) {
        return absenceRepository.save(absence);
    }

    @Override
    public Absence getAbsenceById(Long id) {
        return absenceRepository.findById(id).get();
    }

    @Override
    public Absence updateAbsence(Absence absence) {
        return absenceRepository.save(absence);
    }

    @Override
    public void deleteAbsenceById(Long id) {
        absenceRepository.deleteById(id);
    }
    @Override
    public Page<Absence> findAbsenceByStudent(Long kw , int size , int page)
    {
        return absenceRepository.findAbsenceByStudentId(kw, PageRequest.of(size,page));
    }
}
