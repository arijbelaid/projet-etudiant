package com.example.api_etudiant.controller;

import com.example.api_etudiant.dto.EtudiantDTO;
import com.example.api_etudiant.service.EtudiantService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin(origins = "*")
//@Tag(name = "Gestion des étudiants", description = "API pour la gestion des étudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    @GetMapping
    //@Operation(summary = "Récupérer tous les étudiants")
    public ResponseEntity<List<EtudiantDTO>> getAllEtudiants() {
        return ResponseEntity.ok(etudiantService.getAllEtudiants());
    }

    @GetMapping("/{id}")
    //@Operation(summary = "Récupérer un étudiant par ID")
    public ResponseEntity<EtudiantDTO> getEtudiantById(@PathVariable Long id) {
        return ResponseEntity.ok(etudiantService.getEtudiantById(id));
    }

    @GetMapping("/search")
    //@Operation(summary = "Rechercher par année d'inscription")
    public ResponseEntity<List<EtudiantDTO>> getByAnnee(@RequestParam int annee) {
        return ResponseEntity.ok(etudiantService.getEtudiantsByAnnee(annee));
    }

    @PostMapping
    //@Operation(summary = "Créer un nouvel étudiant")
    public ResponseEntity<EtudiantDTO> createEtudiant(@Valid @RequestBody EtudiantDTO etudiantDTO) {
        EtudiantDTO created = etudiantService.createEtudiant(etudiantDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    //@Operation(summary = "Mettre à jour un étudiant")
    public ResponseEntity<EtudiantDTO> updateEtudiant(@PathVariable Long id,
                                                      @Valid @RequestBody EtudiantDTO etudiantDTO) {
        return ResponseEntity.ok(etudiantService.updateEtudiant(id, etudiantDTO));
    }

    @DeleteMapping("/{id}")
    //@Operation(summary = "Supprimer un étudiant")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
        return ResponseEntity.noContent().build();
    }
}