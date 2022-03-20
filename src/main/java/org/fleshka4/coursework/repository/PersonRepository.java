package org.fleshka4.coursework.repository;

import org.fleshka4.coursework.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    List<Person> findByLastName(String lastName);
}
