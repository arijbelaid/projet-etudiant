package com.example.api_etudiant;

import com.example.api_etudiant.entity.Departement;
import com.example.api_etudiant.entity.Etudiant;
import com.example.api_etudiant.repository.DepartementRepository;
import com.example.api_etudiant.repository.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final EtudiantRepository etudiantRepository;
    private final DepartementRepository departementRepository;

    public DataInitializer(EtudiantRepository etudiantRepository, DepartementRepository departementRepository) {
        this.etudiantRepository = etudiantRepository;
        this.departementRepository = departementRepository;
    }

    @Override
    public void run(String... args) {
        if (etudiantRepository.count() == 0) {
            // 1. Créer les départements
            Departement info = new Departement("Informatique");
            Departement maths = new Departement("Mathématiques");
            Departement physique = new Departement("Physique");

            Departement savedInfo = departementRepository.save(info);
            Departement savedMaths = departementRepository.save(maths);
            Departement savedPhysique = departementRepository.save(physique);

            System.out.println("✅ 3 départements ajoutés");

            // 2. Créer les étudiants avec leur département
            Etudiant e1 = new Etudiant(null, "12345678", "Arij Belaid", LocalDate.of(2003, 1, 15), "arij@example.com", 2022, savedInfo);
            Etudiant e2 = new Etudiant(null, "23456456", "Bader Chetoui", LocalDate.of(2001, 5, 22), "bader@example.com", 2021, savedInfo);
            Etudiant e3 = new Etudiant(null, "12345789", "Chiraz Tabbabi", LocalDate.of(2002, 8, 10), "chiraz@example.com", 2022, savedMaths);
            Etudiant e4 = new Etudiant(null, "09876012", "Mariem Bouaziz", LocalDate.of(2003, 3, 5), "mariem@example.com", 2023, savedMaths);
            Etudiant e5 = new Etudiant(null, "09876345", "Eya Tlili", LocalDate.of(2004, 11, 30), "eya@example.com", 2024, savedPhysique);

            etudiantRepository.saveAll(List.of(e1, e2, e3, e4, e5));

            System.out.println("✅ 5 étudiants ajoutés avec leurs départements");
        }
    }
}