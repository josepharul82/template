package fr.axa.editique.users.application.config.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Si besoin de véhiculer des propriétés dans les autres modules, il faut passer un DTO
 */

@Component
@ConfigurationProperties(prefix = "users-application", ignoreUnknownFields = true)
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersProperties {

    final Logging logging = new Logging();

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Logging {
        int alertWarningMethodExecutionTime;
    }


}
