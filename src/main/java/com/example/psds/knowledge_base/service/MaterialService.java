package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.mapper.ModelLessonAndObjectLesson;
import com.example.psds.knowledge_base.mapper.ModelMaterialAndObjectMaterial;
import com.example.psds.knowledge_base.model.Material;
import com.example.psds.knowledge_base.repository.MaterialRepository;
import com.example.psds.knowledge_base.dto.MaterialDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final ModelMaterialAndObjectMaterial modelMaterialAndObjectMaterial;
    private final ModelLessonAndObjectLesson modelLessonAndObjectLesson;

    public MaterialService(final MaterialRepository materialRepository, final ModelMaterialAndObjectMaterial modelMaterialAndObjectMaterial, final ModelLessonAndObjectLesson modelLessonAndObjectLesson) {
        this.materialRepository = materialRepository;
        this.modelMaterialAndObjectMaterial = modelMaterialAndObjectMaterial;
        this.modelLessonAndObjectLesson = modelLessonAndObjectLesson;
    }

    @Transactional
    public List<MaterialDTO> getMaterialsByLessonId(Long lessonId){
        List<Material> materials = materialRepository.findMaterialsByLesson_Id(lessonId);
        List<MaterialDTO> materialDTOS = new ArrayList<>();

        for (int i=0; i<materials.size(); i++){
            materialDTOS.add(modelMaterialAndObjectMaterial.modelToObject(materials.get(i)));
            /*получили связанный с материалом урок, преобразовали в объект и сохранили в материал*/
            materialDTOS.get(i).setLesson(modelLessonAndObjectLesson.modelToObject(materials.get(i).getLesson()));
        }
        return materialDTOS;
    }
}
