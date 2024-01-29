package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import com.example.psds.knowledge_base.mapper.ModelSpecialistProfileAndObjectSpecialistProfile;
import com.example.psds.knowledge_base.mapper.ModelThemeAndObjectModel;
import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.model.ThemeAndProfile;
import com.example.psds.knowledge_base.repository.SpecialistProfileRepository;
import com.example.psds.knowledge_base.repository.ThemeAndProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialistProfileService {
    private final SpecialistProfileRepository specialistProfileRepository;
    private final ModelSpecialistProfileAndObjectSpecialistProfile modelSpecialistProfileAndObjectSpecialistProfile;
    private final ModelThemeAndObjectModel modelThemeAndObjectModel;

    public SpecialistProfileService(final SpecialistProfileRepository specialistProfileRepository, final ThemeAndProfileRepository themeAndProfileRepository, final ModelSpecialistProfileAndObjectSpecialistProfile modelSpecialistProfileAndObjectSpecialistProfile, final ModelThemeAndObjectModel modelThemeAndObjectModel) {
        this.specialistProfileRepository = specialistProfileRepository;
        this.modelSpecialistProfileAndObjectSpecialistProfile = modelSpecialistProfileAndObjectSpecialistProfile;
        this.modelThemeAndObjectModel = modelThemeAndObjectModel;
    }

    @Transactional
    public List<SpecialistProfileDTO> getSpecialistProfileList(){
        List<SpecialistProfile> specialistProfiles = specialistProfileRepository.findAll();
        List<SpecialistProfileDTO> specialistProfileDTOS = new ArrayList<>();
        /*связи*/
        List<ThemeAndProfile> themeAndProfiles;
        for (int i=0; i<specialistProfiles.size(); i++){
            /*преобразуем*/
            specialistProfileDTOS.add(modelSpecialistProfileAndObjectSpecialistProfile.modelToObject(specialistProfiles.get(i)));
            /*получаем связи*/
            themeAndProfiles = specialistProfiles.get(i).getTapSpecialistProfile();
            for (int j=0; j<themeAndProfiles.size(); j++){
                /*с помощью mapper*/
                specialistProfileDTOS.get(i).getThemes().add(modelThemeAndObjectModel.modelToObject(themeAndProfiles.get(j).getTapTheme()));
            }
        }
        return specialistProfileDTOS;
    }

    @Transactional
    public SpecialistProfile changeSpecialistProfile(SpecialistProfileDTO specialistProfileDTO){
        SpecialistProfile specialistProfile = modelSpecialistProfileAndObjectSpecialistProfile.objectToModel(specialistProfileDTO);
        return specialistProfileRepository.save(specialistProfile);
    }

    @Transactional
    public void deleteSpecialistProfile(Long specialistProfileId){
        specialistProfileRepository.deleteById(specialistProfileId);
    }

    @Transactional
    public SpecialistProfileDTO getSpecialistProfileById(Long specialistProfileId){
        SpecialistProfile specialistProfile = specialistProfileRepository.findSpecialistProfilesById(specialistProfileId);
        SpecialistProfileDTO specialistProfileDTO = modelSpecialistProfileAndObjectSpecialistProfile.modelToObject(specialistProfile);
        List<ThemeAndProfile> themeAndProfiles = specialistProfile.getTapSpecialistProfile();
        for (int i=0; i<themeAndProfiles.size(); i++){
            specialistProfileDTO.getThemes().add(modelThemeAndObjectModel.modelToObject(themeAndProfiles.get(i).getTapTheme()));
        }
        return specialistProfileDTO;
    }
}
