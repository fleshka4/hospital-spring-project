package org.fleshka4.coursework.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;
import java.util.Locale;

public class AuthRequest implements Serializable {
    private String username;
    private String password;
    private String role;

    private final String prefix = "ROLE_";

    @JsonCreator
    public AuthRequest(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = prefix + role.toUpperCase(Locale.ROOT);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = prefix + role.toUpperCase(Locale.ROOT);
    }
}
