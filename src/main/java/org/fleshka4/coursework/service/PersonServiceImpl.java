package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.model.Person;
import org.fleshka4.coursework.model.Ward;
import org.fleshka4.coursework.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@ComponentScan({"org.fleshka4.coursework.repository"})
public class PersonServiceImpl implements PersonService{
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person createPerson(String firstName, String lastName, @Nullable String middleName) {
        return personRepository.save(new Person(firstName, lastName, middleName));
    }

    @Override
    public boolean existsByFullName(String firstName, String lastName, String middleName) {
        return personRepository.existsByFullName(firstName, lastName, middleName);
    }

    @Override
    public boolean existsById(Integer id) {
        return personRepository.existsById(id);
    }

    @Override
    public List<Person> listPeople() {
        return (List<Person>) personRepository.findAll();
    }

    @Override
    public Person findPerson(Integer id) {
        final Optional<Person> person = personRepository.findById(id);
        if (person.isEmpty()) {
            throw new EntityNotFoundException("Person not found");
        }
        return person.get();
    }

    @Override
    public List<Person> findPeopleByLastName(String lastName) {
        return personRepository.findPeopleByLastName(lastName);
    }

    @Override
    public List<Person> findPeopleByFullName(String firstName, String lastName, String middleName) {
        return personRepository.findPeopleByFullName(firstName, lastName, middleName);
    }

    @Override
    public long countByDiagnosisId(Diagnosis diagnosisId) {
        return personRepository.countByDiagnosisId(diagnosisId);
    }

    @Override
    public long countByWardId(Ward wardId) {
        return personRepository.countByWardId(wardId);
    }

    @Override
    public void updateDiagnosisIdById(Diagnosis diagnosisId, Integer id) {
        personRepository.updateDiagnosisIdById(diagnosisId, id);
    }

    @Override
    public void updateWardIdById(Ward wardId, Integer id) {
        personRepository.updateWardIdById(wardId, id);
    }

    @Override
    public void deletePersonById(Integer id) {
        if (!personRepository.existsById(id)) {
            throw new EntityNotFoundException("Person not found");
        }
        personRepository.deleteById(id);
    }
}
