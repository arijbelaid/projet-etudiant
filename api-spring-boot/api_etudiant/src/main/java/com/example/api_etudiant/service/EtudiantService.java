package com.example.api_etudiant.service;

import com.example.api_etudiant.dto.EtudiantDTO;
import com.example.api_etudiant.entity.Etudiant;
import com.example.api_etudiant.exception.ResourceNotFoundException;
import com.example.api_etudiant.mapper.EtudiantMapper;
import com.example.api_etudiant.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private EtudiantMapper etudiantMapper;

    // Récupérer tous les étudiants
    public List<EtudiantDTO> getAllEtudiants() {
        return etudiantRepository.findAll()
                .stream()
                .map(etudiantMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Récupérer par ID avec cache Redis
    @Cacheable(value = "etudiants", key = "#id")
    public EtudiantDTO getEtudiantById(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant non trouvé avec ID: " + id));
        return etudiantMapper.toDTO(etudiant);
    }

    // Créer un étudiant avec éviction du cache

    @CacheEvict(value = "etudiants", allEntries = true)
    public EtudiantDTO createEtudiant(EtudiantDTO etudiantDTO) {
        Etudiant etudiant = etudiantMapper.toEntity(etudiantDTO);
        Etudiant saved = etudiantRepository.save(etudiant);
        return etudiantMapper.toDTO(saved);
    }

    // Mettre à jour
    public EtudiantDTO updateEtudiant(Long id, EtudiantDTO etudiantDTO) {
        Etudiant existing = etudiantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant non trouvé avec ID: " + id));

        existing.setCin(etudiantDTO.getCin());
        existing.setNom(etudiantDTO.getNom());
        existing.setDateNaissance(etudiantDTO.getDateNaissance());
        existing.setEmail(etudiantDTO.getEmail());
        existing.setAnneePremiereInscription(etudiantDTO.getAnneePremiereInscription());

        Etudiant updated = etudiantRepository.save(existing);
        return etudiantMapper.toDTO(updated);
    }

    // Supprimer un étudiant avec éviction du cache

    @CacheEvict(value = "etudiants", allEntries = true)
    public void deleteEtudiant(Long id) {
        if (!etudiantRepository.existsById(id)) {
            throw new RuntimeException("Étudiant non trouvé avec ID: " + id);
        }
        etudiantRepository.deleteById(id);
    }

    // Recherche par année d'inscription
    public List<EtudiantDTO> getEtudiantsByAnnee(int annee) {
        return etudiantRepository.findByAnneePremiereInscription(annee)
                .stream()
                .map(etudiantMapper::toDTO)
                .collect(Collectors.toList());
    }
}