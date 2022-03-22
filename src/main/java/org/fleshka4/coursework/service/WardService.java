package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.model.Ward;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface WardService {
    boolean createWard(String name, Integer maxCount);

    boolean existsByName(String name);

    List<Ward> listWards();

    List<Ward> listWardsWithDiagnosis(Diagnosis diagnosis);

    Optional<Ward> findWard(Integer id);

    List<Ward> findByMaxCountGreaterThan(Integer maxCount);

    boolean deleteWardById(Integer id);
}
