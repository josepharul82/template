package fr.axa.editique.users.repository;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("fr.axa.editique.users.model")
public class UsersTestApplication {
}
