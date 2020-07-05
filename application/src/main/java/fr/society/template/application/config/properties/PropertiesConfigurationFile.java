package fr.society.template.application.config.properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource("classpath:application.yml")
})
public class PropertiesConfigurationFile {
   
}
