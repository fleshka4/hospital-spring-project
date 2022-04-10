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

    @Query("select distinct d from Diagnosis d left join Person p on d=p.diagnosisId" +
            " left join Ward on ?1=p.wardId" +
            " where d=p.diagnosisId and ?1=p.wardId")
    List<Diagnosis> listDiagnosesInWard(Ward ward);
}
