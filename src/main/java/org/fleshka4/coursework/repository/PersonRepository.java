package org.fleshka4.coursework.repository;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.model.Person;
import org.fleshka4.coursework.model.Ward;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    @Query("""
            select (count(p) > 0) from Person p
            where upper(p.firstName) = upper(?1) and upper(p.lastName) = upper(?2)
             and upper(p.middleName) = upper(?3)""")
    boolean existsByFullName(String firstName, String lastName, String middleName);

    @Query("select count(p) from Person p where p.diagnosisId = ?1")
    long countByDiagnosisId(Diagnosis diagnosisId);

    @Query("select count(p) from Person p where p.wardId = ?1")
    long countByWardId(Ward wardId);

    @Transactional
    @Modifying
    @Query("update Person p set p.wardId = ?1 where p.id = ?2")
    int updateWardIdById(Ward wardId, Integer id);

    @Transactional
    @Modifying
    @Query("update Person p set p.diagnosisId = ?1 where p.id = ?2")
    int updateDiagnosisIdById(Diagnosis diagnosisId, Integer id);

    @Query("select p from Person p where p.lastName = ?1")
    List<Person> findByLastName(String lastName);
}
