package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.model.Ward;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DiagnosisService {
    boolean createDiagnosis(String name);

    boolean existsByName(String name);

    List<Diagnosis> listDiagnoses();

    List<Diagnosis> listDiagnosesinWard(Ward ward);

    Optional<Diagnosis> findDiagnosis(Integer id);

    boolean deleteDiagnosisById(Integer id);
}
