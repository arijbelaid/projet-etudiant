package com.example.api_etudiant.repository;

import com.example.api_etudiant.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    // Requête personnalisée Q9
    List<Etudiant> findByAnneePremiereInscription(int annee);

    // Requête JPQL personnalisée
    @Query("SELECT e FROM Etudiant e WHERE e.anneePremiereInscription = :annee")
    List<Etudiant> rechercherParAnneeInscription(@Param("annee") int annee);

    // Recherche par nom contenant
    List<Etudiant> findByNomContainingIgnoreCase(String nom);

}