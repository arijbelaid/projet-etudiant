package com.example.api_etudiant.mapper;

import com.example.api_etudiant.dto.EtudiantDTO;
import com.example.api_etudiant.entity.Etudiant;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {

    public EtudiantDTO toDTO(Etudiant etudiant) {
        if (etudiant == null) return null;

        EtudiantDTO dto = new EtudiantDTO();
        dto.setId(etudiant.getId());
        dto.setCin(etudiant.getCin());
        dto.setNom(etudiant.getNom());
        dto.setDateNaissance(etudiant.getDateNaissance());
        dto.setAge(etudiant.getAge());
        dto.setEmail(etudiant.getEmail());
        dto.setAnneePremiereInscription(etudiant.getAnneePremiereInscription());

        // Ajout du département
        if (etudiant.getDepartement() != null) {
            dto.setDepartementNom(etudiant.getDepartement().getNom());
        }

        return dto;
    }

    public Etudiant toEntity(EtudiantDTO dto) {
        if (dto == null) return null;

        Etudiant etudiant = new Etudiant();
        etudiant.setId(dto.getId());
        etudiant.setCin(dto.getCin());
        etudiant.setNom(dto.getNom());
        etudiant.setDateNaissance(dto.getDateNaissance());
        etudiant.setEmail(dto.getEmail());
        etudiant.setAnneePremiereInscription(dto.getAnneePremiereInscription());

        return etudiant;
    }
}