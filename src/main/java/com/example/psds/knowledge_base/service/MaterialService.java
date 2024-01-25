package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.mapper.MaterialMapper;
import com.example.psds.knowledge_base.repository.MaterialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
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
    public List<com.example.psds.knowledge_base.object.Material> getMaterialsByLessonId(Long lessonId){
        Iterator<com.example.psds.knowledge_base.model.Material> materialsModel = materialRepository.findMaterialsByLesson_Id(lessonId).iterator();
        List<com.example.psds.knowledge_base.object.Material> materialsObject = new ArrayList<>();
        com.example.psds.knowledge_base.model.Material materialModel;
        while((materialModel = materialsModel.next())!=null){
            materialsObject.add(materialMapper.modelToObject(materialModel));
        }
        return materialsObject;
    }
}
