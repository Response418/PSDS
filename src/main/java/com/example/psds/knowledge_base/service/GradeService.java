package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.model.Grade;
import com.example.psds.knowledge_base.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;

    public Grade updateGrade(Long lessonId, Long linkUserId, Integer newGrade){
        Grade grade = gradeRepository.getGradeByLessonIdAndRelationUsersId(lessonId, linkUserId);
        grade.setGrade(newGrade);
        gradeRepository.save(grade);

        return grade;
    }
}
