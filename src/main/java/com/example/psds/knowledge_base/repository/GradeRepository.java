package com.example.psds.knowledge_base.repository;

import com.example.psds.knowledge_base.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    Grade getGradeByLessonIdAndRelationUsersId(Long lessonId, Long relationUsersId);
}