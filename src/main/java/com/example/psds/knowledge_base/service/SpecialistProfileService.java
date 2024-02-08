package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.mapper.ModelSpecialistProfileAndObjectSpecialistProfile;
import com.example.psds.knowledge_base.mapper.ModelThemeAndObjectModel;
import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.model.ThemeAndProfile;
import com.example.psds.knowledge_base.repository.SpecialistProfileRepository;
import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SpecialistProfileService {
    private final SpecialistProfileRepository specialistProfileRepository;
    private final ModelSpecialistProfileAndObjectSpecialistProfile modelSpecialistProfileAndObjectSpecialistProfile;
    private final ModelThemeAndObjectModel modelThemeAndObjectModel;

    @Transactional
    public SpecialistProfileDTO getSpecialistProfileByProfessionProfileId(Long specialistProfileId) {
        SpecialistProfile specialistProfile = specialistProfileRepository.findSpecialistProfileById(specialistProfileId);
        /*с помощью mapper*/
        SpecialistProfileDTO specialistProfileDTO = modelSpecialistProfileAndObjectSpecialistProfile.modelToObject(specialistProfile);
        /*получаем связи*/
        List<ThemeAndProfile> themeAndProfiles = specialistProfile.getTapSpecialistProfile();

        for (int i=0; i<themeAndProfiles.size(); i++){
            /*с помощью mapper*/
            specialistProfileDTO.getThemes().add(modelThemeAndObjectModel.modelToObject(themeAndProfiles.get(i).getTapTheme()));
        }
        return specialistProfileDTO;
    }

}
