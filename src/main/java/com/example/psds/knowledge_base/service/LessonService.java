package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.dto.LessonDTO;
import com.example.psds.knowledge_base.mapper.ModelLessonAndObjectLesson;
import com.example.psds.knowledge_base.model.Lesson;
import com.example.psds.knowledge_base.repository.LessonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ModelLessonAndObjectLesson modelLessonAndObjectLesson;

    public LessonService(final LessonRepository lessonRepository, final ModelLessonAndObjectLesson modelLessonAndObjectLesson) {
        this.lessonRepository = lessonRepository;
        this.modelLessonAndObjectLesson = modelLessonAndObjectLesson;
    }

    @Transactional
    public List<LessonDTO> getLessonList(){
        List<Lesson> lessons = lessonRepository.findAll();
        List<LessonDTO> lessonDTOS = new ArrayList<>();
        for (int i=0; i<lessons.size(); i++){
            lessonDTOS.add(modelLessonAndObjectLesson.modelToObject(lessons.get(i)));
        }
        return lessonDTOS;
    }

    @Transactional
    public void changeLesson(LessonDTO lessonDTO){
        lessonRepository.save(modelLessonAndObjectLesson.objectToModel(lessonDTO));
    }

    @Transactional
    public void deleteLesson(Long lessonId){
        lessonRepository.deleteById(lessonId);
    }

    @Transactional
    public void changeLessonByMaterialId(Long materilaId){
        Lesson lesson = lessonRepository.findLessonByMaterial_Id(materilaId);
        lesson.setMaterial(null);
        lessonRepository.save(lesson);
    }
}
