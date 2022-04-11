package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.model.Person;
import org.fleshka4.coursework.model.Ward;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    Person createPerson(Person person);

    Person createPerson(String firstName, String lastName, @Nullable String middleName);

    boolean existsByFullName(String firstName, String lastName, String middleName);

    boolean existsById(Integer id);

    List<Person> listPeople();

    Person findPerson(Integer id);

    List<Person> findPeopleByLastName(String lastName);

    List<Person> findPeopleByFullName(String firstName, String lastName, @Nullable String middleName);

    long countByDiagnosisId(Diagnosis diagnosisId);

    long countByWardId(Ward wardId);

    void updateDiagnosisIdById(Diagnosis diagnosisId, Integer id);

    void updateWardIdById(Ward wardId, Integer id);

    void deletePersonById(Integer id);
}
