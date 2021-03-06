package org.fleshka4.coursework.main;

import org.fleshka4.coursework.model.User;
import org.fleshka4.coursework.model.UserRole;
import org.fleshka4.coursework.repository.UserRepository;
import org.fleshka4.coursework.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TestDataInit implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsernameIgnoreCase("admin").isPresent()) {
            return;
        }
        final UserRole userRole = new UserRole("ROLE_admin".toUpperCase());
        userRoleRepository.save(userRole);

        userRepository.save(new User("admin",
                bCryptPasswordEncoder.encode("admin"), userRole));
    }
}
