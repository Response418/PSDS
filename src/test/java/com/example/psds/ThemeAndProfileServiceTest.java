package com.example.psds;

import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.model.Theme;
import com.example.psds.knowledge_base.model.ThemeAndProfile;
import com.example.psds.knowledge_base.repository.ThemeAndProfileRepository;
import com.example.psds.knowledge_base.service.ThemeAndProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class ThemeAndProfileServiceTest {

    @Mock
    private ThemeAndProfileRepository themeAndProfileRepository;

    private ThemeAndProfileService themeAndProfileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        themeAndProfileService = new ThemeAndProfileService(themeAndProfileRepository);
    }

    @Test
    void saveThemeAndProfileModels() {
        // Arrange
        SpecialistProfile specialistProfile = new SpecialistProfile();
        List<Theme> themes = new ArrayList<>();
        Theme theme1 = new Theme();
        Theme theme2 = new Theme();
        themes.add(theme1);
        themes.add(theme2);

        // Act
        themeAndProfileService.saveThemeAndProfileModels(specialistProfile, themes);

        // Assert
        verify(themeAndProfileRepository, times(2)).save(any(ThemeAndProfile.class));
    }
}
