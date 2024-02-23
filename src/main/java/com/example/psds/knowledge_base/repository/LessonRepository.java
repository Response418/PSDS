package com.example.psds.knowledge_base.repository;

import com.example.psds.knowledge_base.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Lesson findLessonByMaterials_Id(Long materialId);
    List<Lesson> findByThemeId(Long themeId);
    Lesson findLessonById(Long lessonId);

    Lesson findByIdAndThemeId(Long lessonId, Long themeId);
}
