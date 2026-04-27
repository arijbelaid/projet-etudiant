package com.example.api_etudiant.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "etudiant")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cin;
    private String nom;
    private LocalDate dateNaissance;
    private String email;
    private Integer anneePremiereInscription;
    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;


    // Constructeurs
    public Etudiant() {}

    public Etudiant(Long id, String cin, String nom, LocalDate dateNaissance,
                    String email, Integer anneePremiereInscription, Departement departement) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.anneePremiereInscription = anneePremiereInscription;
        this.departement = departement;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getAnneePremiereInscription() { return anneePremiereInscription; }
    public void setAnneePremiereInscription(Integer anneePremiereInscription) {this.anneePremiereInscription = anneePremiereInscription;
    }
    // Getter et Setter
    public Departement getDepartement() { return departement; }
    public void setDepartement(Departement departement) { this.departement = departement; }
    public int getAge() {
        if (dateNaissance == null) return 0;
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }
}