package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PersonService {
    List<Person> listPeople();
    Optional<Person> findPerson(Integer id);
    List<Person> findPersonByLastName(String lastName);
}
