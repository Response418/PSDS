package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.dto.MaterialDTO;
import com.example.psds.knowledge_base.mapper.ModelMaterialAndObjectMaterial;
import com.example.psds.knowledge_base.model.Material;
import com.example.psds.knowledge_base.repository.MaterialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final ModelMaterialAndObjectMaterial modelMaterialAndObjectMaterial;

    public MaterialService(final MaterialRepository materialRepository, final ModelMaterialAndObjectMaterial modelMaterialAndObjectMaterial) {
        this.materialRepository = materialRepository;
        this.modelMaterialAndObjectMaterial = modelMaterialAndObjectMaterial;
    }

    @Transactional
    public List<MaterialDTO> getMaterialList(){
        List<Material> materials = materialRepository.findAll();
        List<MaterialDTO> materialDTOS = new ArrayList<>();
        for (int i=0; i<materials.size(); i++){
            materialDTOS.add(modelMaterialAndObjectMaterial.modelToObject(materials.get(i)));
        }
        return materialDTOS;
    }

    @Transactional
    public void changeMaterial(MaterialDTO materialDTO){
        materialRepository.save(modelMaterialAndObjectMaterial.objectToModel(materialDTO));
    }

    @Transactional
    public void deleteMaterial(Long materialId){
        materialRepository.deleteById(materialId);
    }
}
