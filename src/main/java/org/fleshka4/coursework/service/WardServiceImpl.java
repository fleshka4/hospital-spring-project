package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.model.Ward;
import org.fleshka4.coursework.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class WardServiceImpl implements WardService{
    @Autowired
    WardRepository wardRepository;

    @Override
    public boolean createWard(String name, Integer maxCount) {
        if (!wardRepository.existsByName(name)) {
            wardRepository.save(new Ward(name, maxCount));
            return true;
        }
        return false;
    }

    @Override
    public boolean existsByName(String name) {
        return wardRepository.existsByName(name);
    }

    @Override
    public List<Ward> listWards() {
        return (List<Ward>) wardRepository.findAll();
    }

    @Override
    public List<Ward> listWardsWithDiagnosis(Diagnosis diagnosis) {
        return wardRepository.listWardsWithDiagnosis(diagnosis);
    }

    @Override
    public Optional<Ward> findWard(Integer id) {
        return wardRepository.findById(id);
    }

    @Override
    public List<Ward> findByMaxCountGreaterThan(Integer maxCount) {
        return wardRepository.findByMaxCountGreaterThan(maxCount);
    }

    @Override
    public boolean deleteWardById(Integer id) {
        if (wardRepository.existsById(id)) {
            wardRepository.deleteById(id);
        }
        return false;
    }
}
