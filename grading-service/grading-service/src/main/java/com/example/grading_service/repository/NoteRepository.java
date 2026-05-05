package com.example.gradingservice.repository;

import com.example.gradingservice.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}