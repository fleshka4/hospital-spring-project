package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Ward;
import org.fleshka4.coursework.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class WardServiceImpl implements WardService{
    @Autowired
    WardRepository wardRepository;

    @Override
    public List<Ward> listWards() {
        return (List<Ward>) wardRepository.findAll();
    }

    @Override
    public Optional<Ward> findWard(Integer id) {
        return wardRepository.findById(id);
    }
}
