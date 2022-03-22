package org.fleshka4.coursework.repository;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.model.Ward;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisRepository extends CrudRepository<Diagnosis, Integer> {
    @Query("select (count(d) > 0) from Diagnosis d where ?1=d.name")
    boolean existsByName(String name);

    @Query("select distinct Diagnosis from Diagnosis d left join Person p left join Ward" +
            " where ?1=p.wardId and d=p.diagnosisId")
    List<Diagnosis> listDiagnosesinWard(Ward ward);
}
