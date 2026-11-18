package com.example.employeemanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileConfigurationTest {
    private PropertySource<?> load(String file) throws IOException {
        return new YamlPropertySourceLoader().load(file, new ClassPathResource(file)).get(0);
    }

    @Test
    void devUsesUpdateAndProdUsesValidate() throws IOException {
        PropertySource<?> dev = load("application-dev.yml");
        PropertySource<?> prod = load("application-prod.yml");

        assertEquals("update", dev.getProperty("spring.jpa.hibernate.ddl-auto"));
        assertEquals("validate", prod.getProperty("spring.jpa.hibernate.ddl-auto"));
        assertEquals("${DB_URL}", prod.getProperty("spring.datasource.url"));
    }
}
