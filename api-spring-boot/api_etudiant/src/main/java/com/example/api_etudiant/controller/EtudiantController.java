package com.example.api_etudiant.controller;

import com.example.api_etudiant.entity.Etudiant;
import com.example.api_etudiant.repository.EtudiantRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin(origins = "*")
public class EtudiantController {

    private final EtudiantRepository repository;

    public EtudiantController(EtudiantRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return repository.findAll();
    }
}