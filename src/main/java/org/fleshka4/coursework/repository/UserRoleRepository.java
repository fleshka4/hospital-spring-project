package org.fleshka4.coursework.repository;

import org.fleshka4.coursework.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    Optional<UserRole> findByRoleIgnoreCase(@NonNull String role);
}
