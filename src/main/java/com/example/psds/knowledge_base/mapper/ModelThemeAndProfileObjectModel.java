package com.example.psds.knowledge_base.mapper;

import com.example.psds.knowledge_base.dto.ThemeAndProfileDTO;
import com.example.psds.knowledge_base.model.ThemeAndProfile;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ModelThemeAndProfileObjectModel {
    ThemeAndProfile objectToModel(ThemeAndProfileDTO themeAndProfileDTO);
    ThemeAndProfileDTO modelToObject(ThemeAndProfile themeAndProfile);
}
