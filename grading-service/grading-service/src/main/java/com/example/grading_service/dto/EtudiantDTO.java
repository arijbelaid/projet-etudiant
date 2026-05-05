package com.example.gradingservice.dto;

import lombok.Data;

@Data
public class EtudiantDTO {
    private Long id;
    private String cin;
    private String nom;
    private String email;
}