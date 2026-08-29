package com.example.api_etudiant.service;

import com.example.api_etudiant.dto.EtudiantDTO;
import com.example.api_etudiant.entity.Etudiant;
import com.example.api_etudiant.mapper.EtudiantMapper;
import com.example.api_etudiant.repository.EtudiantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EtudiantServiceTest {

    @Mock
    private EtudiantRepository repository;

    @Mock
    private EtudiantMapper mapper;

    @InjectMocks
    private EtudiantService service;

    @Test
    void shouldReturnAllEtudiants() {
        Etudiant e = new Etudiant();
        EtudiantDTO dto = new EtudiantDTO();
        when(repository.findAll()).thenReturn(List.of(e));
        when(mapper.toDTO(e)).thenReturn(dto);

        List<EtudiantDTO> result = service.getAllEtudiants();

        assertThat(result).hasSize(1);
        verify(repository).findAll();
    }

    @Test
    void shouldReturnEtudiantById() {
        Etudiant e = new Etudiant();
        e.setId(1L);
        EtudiantDTO dto = new EtudiantDTO();
        dto.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(e));
        when(mapper.toDTO(e)).thenReturn(dto);

        EtudiantDTO result = service.getEtudiantById(1L);

        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void shouldCreateEtudiant() {
        EtudiantDTO input = new EtudiantDTO();
        input.setNom("Test");
        Etudiant entity = new Etudiant();
        Etudiant saved = new Etudiant();
        EtudiantDTO output = new EtudiantDTO();
        output.setNom("Test");

        when(mapper.toEntity(input)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(saved);
        when(mapper.toDTO(saved)).thenReturn(output);

        EtudiantDTO result = service.createEtudiant(input);

        assertThat(result.getNom()).isEqualTo("Test");
        verify(repository).save(entity);
    }
}