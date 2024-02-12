package com.example.psds.knowledge_base.mapper;

import com.example.psds.knowledge_base.dto.ThemeDTO;
import com.example.psds.knowledge_base.model.Theme;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ModelThemeAndObjectModel {
    Theme objectToModel (ThemeDTO themeDTO);
    ThemeDTO modelToObject(Theme theme);
}
