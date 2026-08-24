package se.testkurs.userapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startpunkt for basprojektet.
 *
 * Detta ar ett FARDIGT och FUNGERANDE Spring Boot REST-API for
 * anvandarregistrering. Applikationskoden ska INTE andras av eleverna -
 * kursens fokus ligger helt pa att skriva tester (enhet, komponent,
 * integration) och att bygga CI/CD-pipelines runt detta projekt.
 */
@SpringBootApplication
public class UserRegistrationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserRegistrationApiApplication.class, args);
    }
}
