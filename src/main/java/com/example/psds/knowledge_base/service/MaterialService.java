package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.mapper.MaterialMapper;
import com.example.psds.knowledge_base.repository.MaterialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    public MaterialService(final MaterialRepository materialRepository, final MaterialMapper materialMapper) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
    }

    @Transactional
    public List<com.example.psds.knowledge_base.dto.Material> getMaterialsByLessonId(Long lessonId){
        List<com.example.psds.knowledge_base.model.Material> materialsModel = materialRepository.findMaterialsByLesson_Id(lessonId);
        List<com.example.psds.knowledge_base.dto.Material> materialsObject = new ArrayList<>();
        for(int i=0; i<materialsModel.size(); i++){
            materialsObject.add(materialMapper.modelToObject(materialsModel.get(i)));
        }
        return materialsObject;
    }
}
