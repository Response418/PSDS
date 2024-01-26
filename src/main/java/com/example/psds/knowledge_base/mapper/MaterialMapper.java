package com.example.psds.knowledge_base.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MaterialMapper {
    com.example.psds.knowledge_base.dto.Material modelToObject(com.example.psds.knowledge_base.model.Material material);
    com.example.psds.knowledge_base.model.Material objectToModel(com.example.psds.knowledge_base.dto.Material material);
}
