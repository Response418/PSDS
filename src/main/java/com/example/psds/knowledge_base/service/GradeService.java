package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.dto.GradeDTO;
import com.example.psds.knowledge_base.dto.LessonDTO;
import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import com.example.psds.knowledge_base.dto.ThemeDTO;
import com.example.psds.knowledge_base.mapper.ModelGradeAndObjectGrade;
import com.example.psds.knowledge_base.model.Grade;
import com.example.psds.knowledge_base.repository.GradeRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class GradeService {
    private GradeRepository gradeRepository;
    private ModelGradeAndObjectGrade modelGradeAndObjectGrade;

    @Transactional
    public void addNullGrades(@NotNull SpecialistProfileDTO specialistProfileDTO, Long relationUsersId){
        for (ThemeDTO themeDTO : specialistProfileDTO.getThemes()) {
            for (LessonDTO lessonDTO: themeDTO.getLessons()) {
                GradeDTO gradeDTO = new GradeDTO(Long.valueOf(0L), 0, lessonDTO, relationUsersId);
                Grade grade = modelGradeAndObjectGrade.objectToModel(gradeDTO);
                if(gradeRepository.findGradeByLesson_IdAndRelationUsersId(lessonDTO.getId(), relationUsersId)==null){
                    gradeRepository.save(grade);
                }
            }
        }
    }

    @Transactional
    public Grade getGradeModel(Long lesson_id, Long relationUsersId){
        return gradeRepository.findGradeByLesson_IdAndRelationUsersId(lesson_id, relationUsersId);
    }

    public Grade updateGrade(Long lessonId, Long linkUserId, Integer newGrade){
        Grade grade = gradeRepository.getGradeByLessonIdAndRelationUsersId(lessonId, linkUserId);
        grade.setValue(newGrade);
        gradeRepository.save(grade);

        return grade;
    }
}
