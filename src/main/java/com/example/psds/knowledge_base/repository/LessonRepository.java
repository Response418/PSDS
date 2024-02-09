package com.example.psds.knowledge_base.repository;

import com.example.psds.knowledge_base.model.Lesson;
import com.example.psds.knowledge_base.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByThemeId(Long themeId);

}
