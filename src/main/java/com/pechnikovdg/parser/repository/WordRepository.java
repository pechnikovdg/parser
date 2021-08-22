package com.pechnikovdg.parser.repository;

import com.pechnikovdg.parser.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Integer> {
}
