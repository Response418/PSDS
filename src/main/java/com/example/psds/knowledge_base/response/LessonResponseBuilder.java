package com.example.psds.knowledge_base.response;

import com.example.psds.knowledge_base.dto.GradeDTO;
import com.example.psds.knowledge_base.dto.LessonDTO;
import com.example.psds.knowledge_base.mapper.MapperGrade;
import com.example.psds.knowledge_base.mapper.MapperLesson;
import com.example.psds.knowledge_base.mapper.MapperPlan;
import com.example.psds.knowledge_base.model.Grade;
import com.example.psds.knowledge_base.model.Lesson;
import com.example.psds.knowledge_base.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonResponseBuilder {
    private final LessonService lessonService;
    private final MapperLesson mapperLesson;
    private final MapperGrade mapperGrade;

    public ResponseEntity<?> getLessonByIdAndRelationUsers(Long lessonId, Long userId){
        Grade grade = lessonService.getLessonByIdAndRelationUsers(lessonId, userId);
        GradeDTO gradeDTO = mapperGrade.modelToObject(grade);

        return new ResponseEntity<>(gradeDTO, HttpStatus.OK);
    }
}
