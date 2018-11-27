package br.com.softplan.desafio.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

import br.com.softplan.desafio.api.config.property.DesafioManagementApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(DesafioManagementApiProperty.class)
public class DesafioApiApplication {
    
    private static ApplicationContext applicationContext;
    
    public static void main(final String[] args) {
        applicationContext = SpringApplication.run(DesafioApiApplication.class, args);
    }
    
    public static <T> T getBean(final Class<T> type) {
        return applicationContext.getBean(type);
    }
    
}
