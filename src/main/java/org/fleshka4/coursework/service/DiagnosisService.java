package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.model.Ward;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiagnosisService {
    Diagnosis createDiagnosis(Diagnosis diagnosis);

    Diagnosis createDiagnosis(String name);

    boolean existsByName(String name);

    List<Diagnosis> listDiagnoses();

    List<Diagnosis> listDiagnosesInWard(Ward ward);

    Diagnosis findDiagnosis(Integer id);

    void deleteDiagnosisById(Integer id);
}
