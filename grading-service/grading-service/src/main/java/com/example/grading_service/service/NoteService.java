package com.example.gradingservice.service;

import com.example.gradingservice.dto.NoteDTO;
import com.example.gradingservice.entity.Note;
import com.example.gradingservice.mapper.NoteMapper;
import com.example.gradingservice.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NoteMapper noteMapper;

    public List<NoteDTO> findAll() {
        return noteRepository.findAll().stream()
                .map(noteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public NoteDTO findById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note non trouvée"));
        return noteMapper.toDTO(note);
    }

    public NoteDTO create(NoteDTO dto) {
        Note note = noteMapper.toEntity(dto);
        Note saved = noteRepository.save(note);
        return noteMapper.toDTO(saved);
    }

    public NoteDTO update(Long id, NoteDTO dto) {
        if (!noteRepository.existsById(id))
            throw new RuntimeException("Note non trouvée avec ID: " + id);
        dto.setId(id);
        Note note = noteMapper.toEntity(dto);
        Note updated = noteRepository.save(note);
        return noteMapper.toDTO(updated);
    }

    public void delete(Long id) {
        if (!noteRepository.existsById(id))
            throw new RuntimeException("Note non trouvée avec ID: " + id);
        noteRepository.deleteById(id);
    }
}