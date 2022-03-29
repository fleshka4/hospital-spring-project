package org.fleshka4.coursework.service;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.model.Person;
import org.fleshka4.coursework.model.Ward;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PersonService {
    boolean createPerson(String firstName, String lastName, String middleName);

    boolean existsByFullName(String firstName, String lastName, String middleName);

    List<Person> listPeople();

    Optional<Person> findPerson(Integer id);

    List<Person> findPersonByLastName(String lastName);

    long countByDiagnosisId(Diagnosis diagnosisId);

    long countByWardId(Ward wardId);

    boolean updateDiagnosisIdById(Diagnosis diagnosisId, Integer id);

    boolean updateWardIdById(Ward wardId, Integer id);

    boolean deletePersonById(Integer id);
}
