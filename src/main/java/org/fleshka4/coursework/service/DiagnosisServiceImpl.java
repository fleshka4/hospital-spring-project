package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.model.Ward;
import org.fleshka4.coursework.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@ComponentScan({"org.fleshka4.coursework.repository"})
public class DiagnosisServiceImpl implements DiagnosisService{
    @Autowired
    DiagnosisRepository diagnosisRepository;

    @Override
    public Diagnosis createDiagnosis(Diagnosis diagnosis) {
        if (!diagnosisRepository.existsByName(diagnosis.getName())) {
            return diagnosisRepository.save(diagnosis);
        }
        throw new EntityExistsException("Diagnosis already exists");
    }

    @Override
    public Diagnosis createDiagnosis(String name) {
        if (!diagnosisRepository.existsByName(name)) {
            return diagnosisRepository.save(new Diagnosis(name));
        }
        throw new EntityExistsException("Diagnosis already exists");
    }

    @Override
    public boolean existsByName(String name) {
        return diagnosisRepository.existsByName(name);
    }

    @Override
    public List<Diagnosis> listDiagnoses() {
        return (List<Diagnosis>) diagnosisRepository.findAll();
    }

    @Override
    public List<Diagnosis> listDiagnosesInWard(Ward ward) {
        return diagnosisRepository.listDiagnosesInWard(ward);
    }

    @Override
    public Diagnosis findDiagnosis(Integer id) {
        final Optional<Diagnosis> diagnosis = diagnosisRepository.findById(id);
        if (diagnosis.isEmpty()) {
            throw new EntityNotFoundException("Diagnosis not found");
        }
        return diagnosis.get();
    }

    @Override
    public void deleteDiagnosisById(Integer id) {
        if (!diagnosisRepository.existsById(id)) {
            throw new EntityNotFoundException("Diagnosis not found");
        }
        diagnosisRepository.deleteById(id);
    }
}
