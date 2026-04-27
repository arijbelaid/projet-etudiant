package com.example.api_etudiant.dto;

import java.time.LocalDate;

public class EtudiantDTO {
    private Long id;
    private String cin;
    private String nom;
    private LocalDate dateNaissance;
    private int age;
    private String email;
    private Integer anneePremiereInscription;
    private String departementNom;
    // Constructeurs
    public EtudiantDTO() {}

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getAnneePremiereInscription() { return anneePremiereInscription; }
    public void setAnneePremiereInscription(Integer anneePremiereInscription) {
        this.anneePremiereInscription = anneePremiereInscription;
    }
    public String getDepartementNom() { return departementNom; }
    public void setDepartementNom(String departementNom) { this.departementNom = departementNom; }
}