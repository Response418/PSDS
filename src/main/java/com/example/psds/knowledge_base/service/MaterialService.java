package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.model.Lesson;
import com.example.psds.knowledge_base.model.Material;
import com.example.psds.knowledge_base.repository.MaterialRepository;
import com.example.psds.knowledge_base.responce.LessonResponce;
import com.example.psds.knowledge_base.responce.MaterialResponce;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;

    public MaterialService(final MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Transactional
    public List<MaterialResponce> getMaterialsByLessonId(Long lessonId){
        List<Material> materials = materialRepository.findMaterialsByLesson_Id(lessonId);
        List<MaterialResponce> materialResponces = new ArrayList<>();
        Lesson lesson;
        LessonResponce lessonResponce;
        for (int i=0; i<materials.size(); i++){
            materialResponces.add(i, new MaterialResponce());
            materialResponces.get(i).setId(materials.get(i).getId());
            materialResponces.get(i).setTitle(materials.get(i).getTitle());
            materialResponces.get(i).setDescription(materials.get(i).getDescription());

            lesson = materials.get(i).getLesson();
            lessonResponce = new LessonResponce();
            lessonResponce.setId(lesson.getId());
            lessonResponce.setTitle(lesson.getTitle());
            lessonResponce.setLevel(lesson.getLevel());
            lessonResponce.setDescription(lesson.getDescription());

            materialResponces.get(i).setLesson(lessonResponce);
        }
        return materialResponces;
    }
}
