package com.example.api_etudiant.mapper;

import com.example.api_etudiant.dto.DepartementDTO;
import com.example.api_etudiant.entity.Departement;
import org.springframework.stereotype.Component;

@Component
public class DepartementMapper {

    public DepartementDTO toDTO(Departement departement) {
        if (departement == null) return null;
        return new DepartementDTO(departement.getId(), departement.getNom());
    }

    public Departement toEntity(DepartementDTO dto) {
        if (dto == null) return null;
        Departement departement = new Departement();
        departement.setId(dto.getId());
        departement.setNom(dto.getNom());
        return departement;
    }
}