package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.dto.MaterialDTO;
import com.example.psds.knowledge_base.mapper.ModelMaterialAndObjectMaterial;
import com.example.psds.knowledge_base.model.Material;
import com.example.psds.knowledge_base.repository.MaterialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final ModelMaterialAndObjectMaterial modelMaterialAndObjectMaterial;

    public List<MaterialDTO> getMaterialList(){
        List<Material> materials = materialRepository.findAll();
        List<MaterialDTO> materialDTOS = new ArrayList<>();
        for (Material material : materials) {
            materialDTOS.add(modelMaterialAndObjectMaterial.modelToObject(material));
        }
        return materialDTOS;
    }

    public void changeMaterial(MaterialDTO materialDTO){
        materialRepository.save(modelMaterialAndObjectMaterial.objectToModel(materialDTO));
    }
    public void deleteMaterial(Long materialId) {
        materialRepository.deleteById(materialId);
    }

    public List<MaterialDTO> getMaterialsByLessonId(Long lessonId){
        List<Material> materials = materialRepository.findMaterialsByLesson_Id(lessonId);
        List<MaterialDTO> materialDTOS = new ArrayList<>();

        for (Material material : materials) {
            materialDTOS.add(modelMaterialAndObjectMaterial.modelToObject(material));
        }
        return materialDTOS;
    }
}
