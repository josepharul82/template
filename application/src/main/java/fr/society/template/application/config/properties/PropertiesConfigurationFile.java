package fr.society.template.application.config.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.jndi.JndiPropertySource;

import javax.naming.NamingException;

@Configuration
@PropertySources({
        @PropertySource("classpath:application.yml")
})
public class PropertiesConfigurationFile {
   
}
