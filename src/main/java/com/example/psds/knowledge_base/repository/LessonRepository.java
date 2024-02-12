package com.example.psds.knowledge_base.repository;

import com.example.psds.knowledge_base.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Lesson findLessonByMaterial_Id(Long materialId);
    List<Lesson> findByThemeId(Long themeId);
}
