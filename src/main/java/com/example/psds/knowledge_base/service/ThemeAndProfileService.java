package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.dto.ThemeDTO;
import com.example.psds.knowledge_base.mapper.ModelThemeAndObjectModel;
import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.model.Theme;
import com.example.psds.knowledge_base.model.ThemeAndProfile;
import com.example.psds.knowledge_base.repository.ThemeAndProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ThemeAndProfileService {
    private final ThemeAndProfileRepository themeAndProfileRepository;
    private final ModelThemeAndObjectModel modelThemeAndObjectModel;

    public ThemeAndProfileService(final ThemeAndProfileRepository themeAndProfileRepository, final ModelThemeAndObjectModel modelThemeAndObjectModel) {
        this.themeAndProfileRepository = themeAndProfileRepository;
        this.modelThemeAndObjectModel = modelThemeAndObjectModel;
    }

    @Transactional
    public void saveThemeAndProfileModels(SpecialistProfile specialistProfile, List<Theme> themes){
        ThemeAndProfile themeAndProfile;
        for (int i=0; i< themes.size(); i++){
            themeAndProfile = new ThemeAndProfile();
            themeAndProfile.setTapTheme(themes.get(i));
            themeAndProfile.setTapSpecialistProfileId(specialistProfile);
            themeAndProfileRepository.save(themeAndProfile);
        }
    }
}
