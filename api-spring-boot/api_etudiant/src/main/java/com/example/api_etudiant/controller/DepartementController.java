package com.example.api_etudiant.controller;

import com.example.api_etudiant.dto.DepartementDTO;
import com.example.api_etudiant.entity.Departement;
import com.example.api_etudiant.mapper.DepartementMapper;
import com.example.api_etudiant.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/departements")
@CrossOrigin(origins = "*")
public class DepartementController {

    @Autowired
    private DepartementService departementService;

    @Autowired
    private DepartementMapper departementMapper;

    @GetMapping
    public List<DepartementDTO> getAll() {
        return departementService.findAll()
                .stream()
                .map(departementMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartementDTO> getById(@PathVariable Long id) {
        Departement departement = departementService.findById(id);
        return ResponseEntity.ok(departementMapper.toDTO(departement));
    }

    @PostMapping
    public ResponseEntity<DepartementDTO> create(@RequestBody DepartementDTO dto) {
        Departement departement = departementMapper.toEntity(dto);
        Departement saved = departementService.save(departement);
        return new ResponseEntity<>(departementMapper.toDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartementDTO> update(@PathVariable Long id, @RequestBody DepartementDTO dto) {
        // Récupérer l'entité existante
        Departement existing = departementService.findById(id);
        // Mettre à jour les champs
        existing.setNom(dto.getNom());
        Departement updated = departementService.save(existing);
        return ResponseEntity.ok(departementMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}