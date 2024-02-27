package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.dto.GradeDTO;
import com.example.psds.knowledge_base.dto.LessonDTO;
import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import com.example.psds.knowledge_base.dto.ThemeDTO;
import com.example.psds.knowledge_base.mapper.ModelGradeAndObjectGrade;
import com.example.psds.knowledge_base.model.Grade;
import com.example.psds.knowledge_base.repository.GradeRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private final ModelGradeAndObjectGrade modelGradeAndObjectGrade;

    @Transactional
    public void addNullGrades(@NotNull SpecialistProfileDTO specialistProfileDTO, Long usersId){
        for (ThemeDTO themeDTO : specialistProfileDTO.getThemes()) {
            for (LessonDTO lessonDTO: themeDTO.getLessons()) {
                if (lessonDTO != null) {
                    if(!gradeRepository.existsByLesson_IdAndUsersId(lessonDTO.getId(), usersId)){
                        GradeDTO gradeDTO = new GradeDTO(Long.valueOf(0L), 0, lessonDTO, usersId);
                        Grade grade = modelGradeAndObjectGrade.objectToModel(gradeDTO);
                        gradeRepository.save(grade);
                    }
                }
            }
        }
    }

    @Transactional
    public Grade getGradeModel(Long lesson_id, Long usersId){
        return gradeRepository.findGradeByLesson_IdAndUsersId(lesson_id, usersId);
    }

    public Grade updateGrade(Long lessonId, Long usersId, Integer newGrade){
        Grade grade = gradeRepository.getGradeByLessonIdAndUsersId(lessonId, usersId);
        grade.setValue(newGrade);
        gradeRepository.save(grade);

        return grade;
    }

    public Grade getGradeByLessonAndLink(Long lessonId, Long usersId){
        return gradeRepository.getGradeByLessonIdAndUsersId(lessonId, usersId);
    }
}
