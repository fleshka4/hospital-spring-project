package org.fleshka4.coursework.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "diagnosis", schema = "hospital")
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "diagnosisId", cascade = CascadeType.MERGE)
    private Collection<Person> people;

    public Diagnosis() {}

    public Diagnosis(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Person> getPeople() {
        return people;
    }

    public void setPeople(Collection<Person> people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", people=" + people +
                '}';
    }
}
