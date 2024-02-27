package com.example.psds.knowledge_base.repository;

import com.example.psds.knowledge_base.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    Grade findGradeByLesson_IdAndUsersId(Long lesson_id, Long usersId);
    Grade getGradeByLessonIdAndUsersId(Long lessonId, Long usersId);
    boolean existsByLesson_IdAndUsersId(Long lessonId, Long usersId);
}
