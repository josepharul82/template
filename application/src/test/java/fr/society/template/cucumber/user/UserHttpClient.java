package fr.society.template.cucumber.user;


import fr.society.template.dto.UserDTO;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Component
@Scope(io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserHttpClient {

    @LocalServerPort
    int port;

    final RestTemplate restTemplate;

    final String SERVER_URL = "http://localhost";
    final String THINGS_ENDPOINT = "/api/users";

    public UserHttpClient() {
        this.restTemplate = new RestTemplate();
    }

    private String thingsEndpoint() {
        return SERVER_URL + ":" + port + THINGS_ENDPOINT;
    }

    public UserDTO findById(Integer userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(thingsEndpoint()+"/"+userId);

        HttpEntity<UserDTO> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                UserDTO.class);

        return response.getBody();
    }
}
