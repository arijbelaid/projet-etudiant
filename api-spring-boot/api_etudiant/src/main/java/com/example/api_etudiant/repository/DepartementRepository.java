package com.example.api_etudiant.repository;

import com.example.api_etudiant.entity.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
}