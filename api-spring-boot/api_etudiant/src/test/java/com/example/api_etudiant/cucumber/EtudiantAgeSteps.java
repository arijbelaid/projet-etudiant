package com.example.api_etudiant.cucumber;

import com.example.api_etudiant.entity.Etudiant;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class EtudiantAgeSteps {

    private Etudiant etudiant;

    @Given("un étudiant nommé {string} né le {string}")
    public void unEtudiantNommeNeLe(String nom, String dateNaissance) {
        etudiant = new Etudiant();
        etudiant.setNom(nom);
        etudiant.setDateNaissance(LocalDate.parse(dateNaissance));
        System.out.println("✅ Étudiant créé: " + nom + " né le " + dateNaissance);
    }

    @Then("son âge doit être au moins {int} ans")
    public void sonAgeDoitEtreAuMoinsAns(int ageMin) {
        int age = etudiant.getAge();
        System.out.println("📊 Âge calculé: " + age + " ans");
        assertTrue(age >= ageMin,
                "L'âge (" + age + ") devrait être >= " + ageMin);
    }
}