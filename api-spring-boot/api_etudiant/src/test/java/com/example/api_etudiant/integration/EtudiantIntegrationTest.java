package com.example.api_etudiant.integration;

import com.example.api_etudiant.entity.Etudiant;
import com.example.api_etudiant.repository.EtudiantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class EtudiantIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private EtudiantRepository repository;

    @Test
    void shouldSaveAndFindEtudiant() {
        Etudiant e = new Etudiant();
        e.setCin("TEST01");
        e.setNom("Dupont");
        e.setDateNaissance(LocalDate.of(1990, 1, 1));

        repository.save(e);
        var result = repository.findByCin("TEST01");

        assertThat(result).isPresent();
        assertThat(result.get().getNom()).isEqualTo("Dupont");
    }
}