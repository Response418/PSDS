package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.dto.LessonAndMaterialDTO;
import com.example.psds.knowledge_base.dto.LessonDTO;
import com.example.psds.knowledge_base.dto.MaterialDTO;
import com.example.psds.knowledge_base.mapper.ModelLessonAndObjectLesson;
import com.example.psds.knowledge_base.model.Grade;
import com.example.psds.knowledge_base.model.Lesson;
import com.example.psds.knowledge_base.repository.GradeRepository;
import com.example.psds.knowledge_base.repository.LessonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ModelLessonAndObjectLesson modelLessonAndObjectLesson;
    private final GradeRepository gradeRepository;
    private final MaterialService materialService;

    public List<LessonDTO> getLessonList(){
        List<Lesson> lessons = lessonRepository.findAll();
        List<LessonDTO> lessonDTOS = new ArrayList<>();
        for (int i=0; i<lessons.size(); i++){
            lessonDTOS.add(modelLessonAndObjectLesson.modelToObject(lessons.get(i)));
        }
        return lessonDTOS;
    }

    public void changeLesson(LessonDTO lessonDTO){
        lessonRepository.save(modelLessonAndObjectLesson.objectToModel(lessonDTO));
    }

    public void deleteLesson(Long lessonId){
        lessonRepository.deleteById(lessonId);
    }

    public void changeLessonByMaterialId(Long materilaId){
        Lesson lesson = lessonRepository.findLessonByMaterials_Id(materilaId);
//        lesson.setMaterial(null);
        lessonRepository.save(lesson);
    }

    public Grade getLessonByIdAndRelationUsers(Long lessonId, Long usersId){
        return gradeRepository.getGradeByLessonIdAndUsersId(lessonId, usersId);
    }

    public LessonDTO getLessonById(Long lessonId){
        Lesson lesson = lessonRepository.findLessonById(lessonId);
        return modelLessonAndObjectLesson.modelToObject(lesson);

    }

    public List<LessonDTO> getLessonsForTheme(Long themeId) {
        List<Lesson> lessons = lessonRepository.findByThemeId(themeId);
        List<LessonDTO> lessonDTOS = new ArrayList<>();
        for (Lesson lesson : lessons) {
            lessonDTOS.add(modelLessonAndObjectLesson.modelToObject(lesson));
        }
        return lessonDTOS;
    }

    public void editLessonsAndMaterial(LessonAndMaterialDTO dto) {
        lessonRepository.save(modelLessonAndObjectLesson.objectToModel(dto.getLessonDTO()));
        List<MaterialDTO> materialDTOS = dto.getMaterialDTO();
        for (MaterialDTO materialDTO : materialDTOS) {
            materialService.changeMaterial(materialDTO);
        }
    }
}
