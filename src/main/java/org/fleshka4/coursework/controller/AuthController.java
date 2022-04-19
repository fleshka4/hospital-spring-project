package org.fleshka4.coursework.controller;

import org.fleshka4.coursework.model.User;
import org.fleshka4.coursework.repository.UserRepository;
import org.fleshka4.coursework.model.request.AuthRequest;
import org.fleshka4.coursework.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@ComponentScan("org.fleshka4.coursework.security")
public class AuthController {
    @Autowired
    AuthenticationManager manager;

    @Autowired
    JwtTokenProvider provider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/api/login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthRequest authRequest) {//TODO save salt to the table and check
        try {
            final String name = authRequest.getUsername();
            final User user = userRepository.findByUsernameIgnoreCase(name)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            if (bCryptPasswordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                final String token = provider.createToken(name,
                        user.getUserRole());
                final Map<Object, Object> map = new HashMap<>(2);
                map.put("username", name);
                map.put("token", token);

                return ResponseEntity.ok(map);
            } else {
                throw new BadCredentialsException("Invalid password");
            }
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
