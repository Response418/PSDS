package com.example.psds;

import com.example.psds.knowledge_base.dto.ThemeDTO;
import com.example.psds.knowledge_base.mapper.ModelThemeAndObjectModel;
import com.example.psds.knowledge_base.model.Theme;
import com.example.psds.knowledge_base.repository.ThemeRepository;
import com.example.psds.knowledge_base.service.ThemeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ThemeServiceTest {

    @Mock
    private ThemeRepository themeRepository;

    @Mock
    private ModelThemeAndObjectModel modelThemeAndObjectModel;

    @InjectMocks
    private ThemeService themeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveTheme_shouldSaveThemeAndReturnSavedTheme() {
        // Arrange
        ThemeDTO themeDTO = new ThemeDTO();
        Theme theme = new Theme();
        when(modelThemeAndObjectModel.objectToModel(themeDTO)).thenReturn(theme);
        when(themeRepository.save(theme)).thenReturn(theme);

        // Act
        Theme savedTheme = themeService.saveTheme(themeDTO);

        // Assert
        assertEquals(theme, savedTheme);
        verify(themeRepository, times(1)).save(theme);
    }

    @Test
    void getThemeList_shouldReturnListOfThemes() {
        // Arrange
        List<Theme> themes = new ArrayList<>();
        themes.add(new Theme());
        themes.add(new Theme());
        when(themeRepository.findAll()).thenReturn(themes);

        // Act
        List<ThemeDTO> themeDTOS = themeService.getThemeList();

        // Assert
        assertEquals(themes.size(), themeDTOS.size());
        verify(themeRepository, times(1)).findAll();
        verify(modelThemeAndObjectModel, times(themes.size())).modelToObject(any(Theme.class));
    }
}