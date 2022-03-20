package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Ward;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface WardService {
    List<Ward> listWards();
    Optional<Ward> findWard(Integer id);
}
