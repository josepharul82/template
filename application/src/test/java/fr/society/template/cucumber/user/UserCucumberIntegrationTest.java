package fr.society.template.cucumber.user;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/user.feature",
        plugin = {"pretty", "html:target/cucumber/features/user"},
        extraGlue = "fr.axa.editique.users.cucumber.user.common")
public class UserCucumberIntegrationTest {
}
