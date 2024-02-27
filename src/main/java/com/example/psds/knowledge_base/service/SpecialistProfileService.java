package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import com.example.psds.knowledge_base.mapper.ModelSpecialistProfileAndObjectSpecialistProfile;
import com.example.psds.knowledge_base.mapper.ModelThemeAndObjectModel;
import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.model.ThemeAndProfile;
import com.example.psds.knowledge_base.repository.SpecialistProfileRepository;
import com.example.psds.knowledge_base.repository.ThemeRepository;
import lombok.AllArgsConstructor;
import com.example.psds.knowledge_base.dto.LessonDTO;
import com.example.psds.knowledge_base.dto.ThemeDTO;
import com.example.psds.knowledge_base.mapper.ModelLessonAndObjectLesson;
import com.example.psds.knowledge_base.model.Lesson;
import com.example.psds.knowledge_base.model.Theme;
import com.example.psds.knowledge_base.repository.LessonRepository;
import com.example.psds.knowledge_base.repository.ThemeAndProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SpecialistProfileService {
    private final SpecialistProfileRepository specialistProfileRepository;
    private final ModelSpecialistProfileAndObjectSpecialistProfile modelSpecialistProfileAndObjectSpecialistProfile;
    private final ModelThemeAndObjectModel modelThemeAndObjectModel;
    private final ThemeAndProfileRepository themeAndProfileRepository;
    private final LessonRepository lessonRepository;
    private final ModelLessonAndObjectLesson modelLessonAndObjectLesson;


    public List<SpecialistProfileDTO> getSpecialistProfileList(){
        List<SpecialistProfile> specialistProfiles = specialistProfileRepository.findAll();
        List<SpecialistProfileDTO> specialistProfileDTOS = new ArrayList<>();
        List<ThemeAndProfile> themeAndProfiles;
        for (int i=0; i<specialistProfiles.size(); i++){
            specialistProfileDTOS.add(modelSpecialistProfileAndObjectSpecialistProfile.modelToObject(specialistProfiles.get(i)));
            themeAndProfiles = specialistProfiles.get(i).getTapSpecialistProfile();
            for (int j=0; j<themeAndProfiles.size(); j++){
                specialistProfileDTOS.get(i).getThemes().add(modelThemeAndObjectModel.modelToObject(themeAndProfiles.get(j).getTapTheme()));
            }
        }
        return specialistProfileDTOS;
    }

    public SpecialistProfile changeSpecialistProfile(SpecialistProfileDTO specialistProfileDTO){
        SpecialistProfile specialistProfile = modelSpecialistProfileAndObjectSpecialistProfile.objectToModel(specialistProfileDTO);
        return specialistProfileRepository.save(specialistProfile);
    }

    public void deleteSpecialistProfile(Long specialistProfileId){
        specialistProfileRepository.deleteById(specialistProfileId);
    }

    public SpecialistProfileDTO getSpecialistProfileById(Long specialistProfileId) {
        SpecialistProfile specialistProfile = specialistProfileRepository.findSpecialistProfilesById(specialistProfileId);
        if (specialistProfile != null) {
            SpecialistProfileDTO specialistProfileDTO = modelSpecialistProfileAndObjectSpecialistProfile.modelToObject(specialistProfile);
            List<ThemeAndProfile> themeAndProfiles = specialistProfile.getTapSpecialistProfile();
            for (int i = 0; i < themeAndProfiles.size(); i++) {
                specialistProfileDTO.getThemes().add(modelThemeAndObjectModel.modelToObject(themeAndProfiles.get(i).getTapTheme()));
            }
            return specialistProfileDTO;
        }
        return null;
    }

    @Transactional
    public List<SpecialistProfileDTO> getSpecialistProfilesByString(String searchString) {
        List<SpecialistProfile> findedSpecialistProfiles = specialistProfileRepository.findByTitleContainingIgnoreCase(searchString);
        List<SpecialistProfileDTO> specialistProfileDTOs = new ArrayList<>();

        for (SpecialistProfile specialistProfile : findedSpecialistProfiles) {
            SpecialistProfileDTO specialistProfileDTO = modelSpecialistProfileAndObjectSpecialistProfile.modelToObject(specialistProfile);
            specialistProfileDTO.setThemes(getThemesBySpecialistProfileId(specialistProfile));
            specialistProfileDTOs.add(specialistProfileDTO);
        }

        return specialistProfileDTOs;
    }

    private List<ThemeDTO> getThemesBySpecialistProfileId(SpecialistProfile specialistProfileId) {
        List<ThemeAndProfile> themeAndProfile = themeAndProfileRepository.findByTapSpecialistProfileId(specialistProfileId);

        List<Theme> themes = new ArrayList<>();

        for (ThemeAndProfile pair : themeAndProfile) {
            themes.add(pair.getTapTheme());
        }
        List<ThemeDTO> themeDTOs = new ArrayList<>();
        for (Theme theme : themes) {
            ThemeDTO themeDTO = modelThemeAndObjectModel.modelToObject(theme);
            themeDTO.setLessons(getLessonsByThemeId(theme.getId()));
            themeDTOs.add(themeDTO);
        }
        return themeDTOs;
    }

    private List<LessonDTO> getLessonsByThemeId(Long themeId) {
        List<Lesson> lessons = lessonRepository.findByThemeId(themeId);
        List<LessonDTO> lessonDTOs = new ArrayList<>();
        for (Lesson lesson : lessons) {
            LessonDTO lessonDTO = modelLessonAndObjectLesson.modelToObject(lesson);
            lessonDTOs.add(lessonDTO);
        }
        return lessonDTOs;
    }

}
