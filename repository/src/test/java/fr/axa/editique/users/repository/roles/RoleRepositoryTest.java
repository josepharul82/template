package fr.axa.editique.users.repository.roles;

import fr.axa.editique.users.model.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 *
 * @RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test features and JUnit. Whenever we are using any Spring Boot testing features in our JUnit tests, this annotation will be required.
 * @DataJpaTest provides some standard setup needed for testing the persistence layer:
 *
 * configuring H2, an in-memory database
 * setting Hibernate, Spring Data, and the DataSource
 * performing an @EntityScan
 * turning on SQL logging
 *
 *
 */

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testRepository(){
        List<Role> roleList = roleRepository.findAll();
        Assert.assertTrue("No data for role",roleList.size()>0);;
    }
}
