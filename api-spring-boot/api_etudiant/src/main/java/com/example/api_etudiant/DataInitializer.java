package com.example.api_etudiant;

import com.example.api_etudiant.entity.Etudiant;
import com.example.api_etudiant.repository.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final EtudiantRepository repository;

    public DataInitializer(EtudiantRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            repository.saveAll(List.of(
                    new Etudiant(null, "12345678", "arij belaid", LocalDate.of(2003, 1, 15)),
                    new Etudiant(null, "23456456", "bader chetoui", LocalDate.of(2001, 5, 22)),
                    new Etudiant(null, "12345789", "chiraz tabbabi", LocalDate.of(2002, 8, 10)),
                    new Etudiant(null, "09876012", "Mariem bouaziz", LocalDate.of(2003, 3, 5)),
                    new Etudiant(null, "09876345", "Eya tlili", LocalDate.of(2004, 11, 30))
            ));
            System.out.println("✅ 5 étudiants ajoutés à la base de données");
        }
    }
}