package com.example.psds.knowledge_base.repository;

import com.example.psds.knowledge_base.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
