package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DiagnosisServiceImpl implements DiagnosisService{
    @Autowired
    DiagnosisRepository diagnosisRepository;

    @Override
    public List<Diagnosis> listDiagnoses() {
        return (List<Diagnosis>) diagnosisRepository.findAll();
    }

    @Override
    public Optional<Diagnosis> findDiagnosis(Integer id) {
        return diagnosisRepository.findById(id);
    }
}
