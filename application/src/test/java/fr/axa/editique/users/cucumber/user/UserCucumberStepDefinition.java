package fr.axa.editique.users.cucumber.user;


import fr.axa.editique.users.dto.UserDTO;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Log
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCucumberStepDefinition {

    @Autowired
    UserHttpClient userHttpClient;
    UserDTO userDTO;

    @When("^the client calls (\\d+)$" )
    public void the_client_calls_api_users(final int userId) {
        userDTO = userHttpClient.findById(userId);
    }

    @Then("^the client receives user (\\d+)$")
    public void the_client_receives_user(final int userId) {
        Assert.assertEquals(Long.valueOf(userId), java.util.Optional.ofNullable(userDTO.getId()).get());
    }



    /*
    Given("I have (\\d+) in my wallet", (Integer money) -> budget = money);

    When("I buy .* with (\\d+)", (Integer price) -> budget -= price);

    Then("I should have (\\d+) in my wallet", (Integer finalBudget) ->
    assertEquals(budget, finalBudget.intValue()));



    @Wh ("^I put (\\d+) (\\w+) in the bag$")
    public void i_put_something_in_the_bag(final int quantity, final String something) {
        IntStream.range(0, quantity)
                .peek(n -> log.info("Putting a {} in the bag, number {}", something, quantity))
                .map(ignore -> bagHttpClient.put(something))
                .forEach(statusCode -> assertThat(statusCode).isEqualTo(HttpStatus.CREATED.value()));
    }

    @Then("^the bag should contain only (\\d+) (\\w+)$")
    public void the_bag_should_contain_only_something(final int quantity, final String something) {
        assertNumberOfTimes(quantity, something, true);
    }

    @Then("^the bag should contain (\\d+) (\\w+)$")
    public void the_bag_should_contain_something(final int quantity, final String something) {
        assertNumberOfTimes(quantity, something, false);
    }

    private void assertNumberOfTimes(final int quantity, final String something, final boolean onlyThat) {
        final List<String> things = bagHttpClient.getContents().getThings();
        log.info("Expecting {} times {}. The bag contains {}", quantity, something, things);
        final int timesInList = Collections.frequency(things, something);
        assertThat(timesInList).isEqualTo(quantity);
        if (onlyThat) {
            assertThat(timesInList).isEqualTo(things.size());
        }
    }
    */

}
