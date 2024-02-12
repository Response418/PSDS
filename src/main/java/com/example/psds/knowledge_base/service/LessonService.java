package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.model.Grade;
import com.example.psds.knowledge_base.model.Lesson;
import com.example.psds.knowledge_base.model.Plan;
import com.example.psds.knowledge_base.repository.GradeRepository;
import com.example.psds.knowledge_base.repository.LessonRepository;
import com.example.psds.knowledge_base.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final GradeRepository gradeRepository;

    public Grade getLessonByIdAndRelationUsers(Long lessonId, Long linkUserId){
        return gradeRepository.getGradeByLessonIdAndRelationUsersId(lessonId, linkUserId);
    }
}
