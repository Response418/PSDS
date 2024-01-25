package com.example.psds.knowledge_base.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MaterialMapper {
    com.example.psds.knowledge_base.object.Material modelToObject(com.example.psds.knowledge_base.Model.Material material);
    com.example.psds.knowledge_base.Model.Material objectToModel(com.example.psds.knowledge_base.object.Material material);
}
