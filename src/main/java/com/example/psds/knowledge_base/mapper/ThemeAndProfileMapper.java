package com.example.psds.knowledge_base.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ThemeAndProfileMapper {
    com.example.psds.knowledge_base.object.ThemeAndProfile modelToObject(com.example.psds.knowledge_base.Model.ThemeAndProfile themeAndProfile);
    com.example.psds.knowledge_base.Model.ThemeAndProfile objectToModel(com.example.psds.knowledge_base.object.ThemeAndProfile themeAndProfile);
}


