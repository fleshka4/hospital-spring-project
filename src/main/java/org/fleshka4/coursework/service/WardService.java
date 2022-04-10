package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.model.Ward;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WardService {
    Ward createWard(Ward ward);

    Ward createWard(String name, Integer maxCount);

    boolean existsByName(String name);

    List<Ward> listWards();

    List<Ward> listWardsWithDiagnosis(Diagnosis diagnosis);

    Ward findWard(Integer id);

    List<Ward> findByMaxCountGreaterThan(Integer maxCount);

    void deleteWardById(Integer id);
}
