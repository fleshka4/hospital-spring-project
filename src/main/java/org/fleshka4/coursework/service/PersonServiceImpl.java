package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Person;
import org.fleshka4.coursework.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PersonServiceImpl implements PersonService{
    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Person> listPeople() {
        return (List<Person>) personRepository.findAll();
    }

    @Override
    public Optional<Person> findPerson(Integer id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findPersonByLastName(String lastName) {
        return personRepository.findByLastName(lastName);
    }
}
