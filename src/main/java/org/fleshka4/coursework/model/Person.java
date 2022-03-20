package org.fleshka4.coursework.model;

import javax.persistence.*;

@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "firstname", length = 20)
    private String firstName;

    @Column(name = "lastname", length = 20)
    private String lastName;

    @Column(name = "middlename", length = 20)
    private String middleName;

    @ManyToOne
    @JoinColumn(name = "diagnosisid")
    private Diagnosis diagnosisId;

    @ManyToOne
    @JoinColumn(name = "wardid")
    private Ward wardId;

    public Person() {}

    public Person(String firstName, String lastName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Diagnosis getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(Diagnosis diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public Ward getWardId() {
        return wardId;
    }

    public void setWardId(Ward wardId) {
        this.wardId = wardId;
    }
}
