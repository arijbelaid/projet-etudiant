package com.example.api_etudiant.service;

import com.example.api_etudiant.entity.Departement;
import com.example.api_etudiant.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    public List<Departement> findAll() {
        return departementRepository.findAll();
    }

    public Departement findById(Long id) {
        return departementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Département non trouvé avec ID: " + id));
    }

    public Departement save(Departement departement) {
        return departementRepository.save(departement);
    }

    public void deleteById(Long id) {
        if (!departementRepository.existsById(id)) {
            throw new RuntimeException("Département non trouvé avec ID: " + id);
        }
        departementRepository.deleteById(id);
    }
}