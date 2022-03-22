package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.model.Person;
import org.fleshka4.coursework.model.Ward;
import org.fleshka4.coursework.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PersonServiceImpl implements PersonService{
    @Autowired
    PersonRepository personRepository;

    @Override
    public boolean createPerson(String firstName, String lastName, String middleName) {
        if (!personRepository.existsByFullName(firstName, lastName, middleName)) {
            personRepository.save(new Person(firstName, lastName, middleName));
            return true;
        }
        return false;
    }

    @Override
    public boolean existsByFullName(String firstName, String lastName, String middleName) {
        return personRepository.existsByFullName(firstName, lastName, middleName);
    }

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

    @Override
    public long countByDiagnosisId(Diagnosis diagnosisId) {
        return personRepository.countByDiagnosisId(diagnosisId);
    }

    @Override
    public long countByWardId(Ward wardId) {
        return personRepository.countByWardId(wardId);
    }

    @Override
    public boolean updateDiagnosisIdById(Diagnosis diagnosisId, Integer id) {
        return personRepository.updateDiagnosisIdById(diagnosisId, id) == 0;
    }

    @Override
    public boolean updateWardIdById(Ward wardId, Integer id) {
        return personRepository.updateWardIdById(wardId, id) == 0;
    }

    @Override
    public boolean deletePersonById(Integer id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
