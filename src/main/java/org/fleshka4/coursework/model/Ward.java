package org.fleshka4.coursework.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "wards")
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "maxcount")
    private Integer maxCount;

    @OneToMany(mappedBy = "wardId")
    private Collection<Person> people;

    public Ward() {}

    public Ward(String name, Integer maxCount) {
        this.name = name;
        this.maxCount = maxCount;
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

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public Collection<Person> getPeople() {
        return people;
    }

    public void setPeople(Collection<Person> people) {
        this.people = people;
    }
}
