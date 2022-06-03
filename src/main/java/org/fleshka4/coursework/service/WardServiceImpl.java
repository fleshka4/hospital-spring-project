package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.model.Ward;
import org.fleshka4.coursework.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@ComponentScan({"org.fleshka4.coursework.repository"})
public class WardServiceImpl implements WardService{
    @Autowired
    private WardRepository wardRepository;

    @Override
    public Ward createWard(Ward ward) {
        if (!wardRepository.existsByName(ward.getName())) {
            return wardRepository.save(ward);
        }
        throw new EntityExistsException("Ward already exists");
    }

    @Override
    public Ward createWard(String name, Integer maxCount) {
        if (!wardRepository.existsByName(name)) {
            return wardRepository.save(new Ward(name, maxCount));
        }
        throw new EntityExistsException("Ward already exists");
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
    public Ward findWard(Integer id) {
        final Optional<Ward> ward = wardRepository.findById(id);
        if (ward.isEmpty()) {
            throw new EntityNotFoundException("Ward not found");
        }
        return ward.get();
    }

    @Override
    public List<Ward> findByMaxCountGreaterThan(Integer maxCount) {
        return wardRepository.findByMaxCountGreaterThan(maxCount);
    }

    @Override
    public void deleteWardById(Integer id) {
        if (!wardRepository.existsById(id)) {
            throw new EntityNotFoundException("Ward not found");
        }
        wardRepository.deleteById(id);
    }
}
