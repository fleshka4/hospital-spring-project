package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.model.Ward;
import org.fleshka4.coursework.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DiagnosisServiceImpl implements DiagnosisService{
    @Autowired
    DiagnosisRepository diagnosisRepository;

    @Override
    public boolean createDiagnosis(String name) {
        if (!diagnosisRepository.existsByName(name)) {
            diagnosisRepository.save(new Diagnosis(name));
            return true;
        }
        return false;
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
    public List<Diagnosis> listDiagnosesinWard(Ward ward) {
        return diagnosisRepository.listDiagnosesinWard(ward);
    }

    @Override
    public Optional<Diagnosis> findDiagnosis(Integer id) {
        return diagnosisRepository.findById(id);
    }

    @Override
    public boolean deleteDiagnosisById(Integer id) {
        if (diagnosisRepository.existsById(id)) {
            diagnosisRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
