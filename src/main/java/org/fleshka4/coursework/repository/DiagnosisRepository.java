package org.fleshka4.coursework.repository;

import org.fleshka4.coursework.model.Diagnosis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisRepository extends CrudRepository<Diagnosis, Integer> {}
