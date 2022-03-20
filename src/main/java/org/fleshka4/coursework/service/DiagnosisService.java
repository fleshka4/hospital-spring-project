package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Diagnosis;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DiagnosisService {
    List<Diagnosis> listDiagnoses();
    Optional<Diagnosis> findDiagnosis(Integer id);
}
