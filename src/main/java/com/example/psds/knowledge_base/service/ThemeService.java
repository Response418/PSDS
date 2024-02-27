package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.dto.MaterialDTO;
import com.example.psds.knowledge_base.dto.ThemeAndLessonAndMaterialsDTO;
import com.example.psds.knowledge_base.dto.ThemeDTO;
import com.example.psds.knowledge_base.mapper.ModelLessonAndObjectLesson;
import com.example.psds.knowledge_base.mapper.ModelMaterialAndObjectMaterial;
import com.example.psds.knowledge_base.mapper.ModelThemeAndObjectModel;
import com.example.psds.knowledge_base.model.Lesson;
import com.example.psds.knowledge_base.model.Material;
import com.example.psds.knowledge_base.model.Theme;
import com.example.psds.knowledge_base.repository.LessonRepository;
import com.example.psds.knowledge_base.repository.MaterialRepository;
import com.example.psds.knowledge_base.repository.ThemeRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final ModelThemeAndObjectModel modelThemeAndObjectModel;
    private final LessonRepository lessonRepository;
    private final MaterialRepository materialRepository;
    private final ModelLessonAndObjectLesson modelLessonAndObjectLesson;
    private final ModelMaterialAndObjectMaterial modelMaterialAndObjectMaterial;

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

    public void deleteLessonFromTheme(Long themeId, Long lessonId) {
        Lesson lesson = lessonRepository.findByIdAndThemeId(lessonId, themeId);
    }

    public void addLessonAndMaterialForTheme(@NotNull ThemeAndLessonAndMaterialsDTO dto) {
        Theme theme = themeRepository.findThemeById(dto.getThemeId());
        Lesson lesson = lessonRepository.save(modelLessonAndObjectLesson.objectToModel(dto.getLessonDTO()));
        lesson.setTheme(theme);
        lessonRepository.save(lesson);
        log.info("Saving new lesson {} for theme {}", lesson.getId(), theme.getId());
        List<MaterialDTO> materialDTOS = dto.getMaterialDTO();
        for (MaterialDTO materialDTO : materialDTOS) {
            Material material = materialRepository.save(modelMaterialAndObjectMaterial.objectToModel(materialDTO));
            material.setLesson(lesson);
            materialRepository.save(material);
            log.info("Saving new material {} for lesson {}", material.getId(), lesson.getId());
        }
    }

}
