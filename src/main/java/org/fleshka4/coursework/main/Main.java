package org.fleshka4.coursework.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"org.fleshka4.coursework.controller", "org.fleshka4.coursework.service", "org.fleshka4.coursework.main"})
@EntityScan("org.fleshka4.coursework.model")
@EnableJpaRepositories("org.fleshka4.coursework.repository")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
