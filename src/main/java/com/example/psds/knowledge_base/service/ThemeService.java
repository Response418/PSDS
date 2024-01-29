package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.dto.ThemeDTO;
import com.example.psds.knowledge_base.mapper.ModelThemeAndObjectModel;
import com.example.psds.knowledge_base.model.Theme;
import com.example.psds.knowledge_base.repository.ThemeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final ModelThemeAndObjectModel modelThemeAndObjectModel;

    public ThemeService(final ThemeRepository themeRepository, final ModelThemeAndObjectModel modelThemeAndObjectModel) {
        this.themeRepository = themeRepository;
        this.modelThemeAndObjectModel = modelThemeAndObjectModel;
    }

    @Transactional
    public Theme saveTheme(ThemeDTO themeDTO){
        return themeRepository.save(modelThemeAndObjectModel.objectToModel(themeDTO));
    }
}
