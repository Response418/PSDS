package com.example.psds.knowledge_base.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ThemeAndProfileMapper {
    com.example.psds.knowledge_base.object.ThemeAndProfile modelToObject(com.example.psds.knowledge_base.model.ThemeAndProfile themeAndProfile);
    com.example.psds.knowledge_base.model.ThemeAndProfile objectToModel(com.example.psds.knowledge_base.object.ThemeAndProfile themeAndProfile);
}


