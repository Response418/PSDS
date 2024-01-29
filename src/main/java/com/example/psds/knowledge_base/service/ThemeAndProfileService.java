package com.example.psds.knowledge_base.service;

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

    public ThemeAndProfileService(final ThemeAndProfileRepository themeAndProfileRepository) {
        this.themeAndProfileRepository = themeAndProfileRepository;
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
