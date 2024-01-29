package com.example.psds.knowledge_base.mapper;

import com.example.psds.knowledge_base.dto.MaterialDTO;
import com.example.psds.knowledge_base.model.Material;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ModelMaterialAndObjectMaterial {
    Material objectToModel(MaterialDTO materialDTO);
    MaterialDTO modelToObject(Material material);
}