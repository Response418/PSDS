package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.dto.ThemeDTO;
import com.example.psds.knowledge_base.mapper.ModelThemeAndObjectModel;
import com.example.psds.knowledge_base.model.Theme;
import com.example.psds.knowledge_base.repository.ThemeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final ModelThemeAndObjectModel modelThemeAndObjectModel;

    public Theme saveTheme(ThemeDTO themeDTO){
        return themeRepository.save(modelThemeAndObjectModel.objectToModel(themeDTO));
    }

    public List<ThemeDTO> getThemeList(){
        List<Theme> themes = themeRepository.findAll();
        List<ThemeDTO> themeDTOS = new ArrayList<>();
        for(int i=0; i<themes.size(); i++){
            themeDTOS.add(modelThemeAndObjectModel.modelToObject(themes.get(i)));
        }
        return themeDTOS;
    }

    public void changeTheme(ThemeDTO themeDTO){
        Theme theme = modelThemeAndObjectModel.objectToModel(themeDTO);
        themeRepository.save(theme);
    }

    public void deleteTheme(Long themeId){
        themeRepository.deleteById(themeId);
    }

    public ThemeDTO getThemeById(Long themeId) {
        Theme theme = themeRepository.findThemeById(themeId);
        return modelThemeAndObjectModel.modelToObject(theme);
    }
}
