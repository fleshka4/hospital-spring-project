package org.fleshka4.coursework.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user_roles", schema = "hospital")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_role", nullable = false, unique = true)
    private String role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userRole",
            cascade = CascadeType.MERGE)
    private Collection<User> users;

    public UserRole() {}

    public UserRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}