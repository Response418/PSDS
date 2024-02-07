package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import com.example.psds.knowledge_base.mapper.ModelSpecialistProfileAndObjectSpecialistProfile;
import com.example.psds.knowledge_base.mapper.ModelThemeAndObjectModel;
import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.model.ThemeAndProfile;
import com.example.psds.knowledge_base.repository.SpecialistProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialistProfileService {
    private final SpecialistProfileRepository specialistProfileRepository;
    private final ModelSpecialistProfileAndObjectSpecialistProfile modelSpecialistProfileAndObjectSpecialistProfile;
    private final ModelThemeAndObjectModel modelThemeAndObjectModel;

    public SpecialistProfileService(final SpecialistProfileRepository specialistProfileRepository, final ModelSpecialistProfileAndObjectSpecialistProfile modelSpecialistProfileAndObjectSpecialistProfile, final ModelThemeAndObjectModel modelThemeAndObjectModel) {
        this.specialistProfileRepository = specialistProfileRepository;
        this.modelSpecialistProfileAndObjectSpecialistProfile = modelSpecialistProfileAndObjectSpecialistProfile;
        this.modelThemeAndObjectModel = modelThemeAndObjectModel;
    }

    @Transactional
    public List<SpecialistProfileDTO> getSpecialistProfilesByString(String searchString) {
        List<SpecialistProfile> foundProfiles = specialistProfileRepository.findSpecialistProfilesByTitle(searchString);
        List<SpecialistProfileDTO> foundProfileDTOs = new ArrayList<>();

        for (SpecialistProfile profile : foundProfiles) {
            SpecialistProfileDTO profileDTO = modelSpecialistProfileAndObjectSpecialistProfile.modelToObject(profile);
            foundProfileDTOs.add(profileDTO);
        }

        return foundProfileDTOs;
    }

}
