package org.fleshka4.coursework.repository;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.model.Ward;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends CrudRepository<Ward, Integer> {
    @Query("select w from Ward w where w.maxCount > ?1")
    List<Ward> findByMaxCountGreaterThan(Integer maxCount);

    @Query("select distinct Ward from Ward w left join Person p left join Diagnosis" +
            " where w=p.wardId and ?1=p.diagnosisId")
    List<Ward> listWardsWithDiagnosis(Diagnosis diagnosis);

    @Query("select (count(w) > 0) from Ward w where upper(w.name) = upper(?1)")
    boolean existsByName(String name);
}