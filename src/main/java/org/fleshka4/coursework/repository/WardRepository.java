package org.fleshka4.coursework.repository;

import org.fleshka4.coursework.model.Ward;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends CrudRepository<Ward, Integer> {}
