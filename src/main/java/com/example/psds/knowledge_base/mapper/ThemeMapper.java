package com.example.psds.knowledge_base.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ThemeMapper {
    com.example.psds.knowledge_base.dto.Theme modelToObject(com.example.psds.knowledge_base.model.Theme theme);
    com.example.psds.knowledge_base.model.Theme objectToModel(com.example.psds.knowledge_base.dto.Theme theme);
}
