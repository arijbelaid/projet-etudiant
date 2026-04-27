package com.example.api_etudiant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departements")
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;
    @JsonIgnore  // ← AJOUTE CETTE LIGNE pour ignorer la liste des étudiants

    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    private List<Etudiant> etudiants = new ArrayList<>();

    public Departement() {}

    public Departement(String nom) {
        this.nom = nom;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public List<Etudiant> getEtudiants() { return etudiants; }
    public void setEtudiants(List<Etudiant> etudiants) { this.etudiants = etudiants; }
}