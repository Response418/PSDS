package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.mapper.SpecialistProfileMapper;
import com.example.psds.knowledge_base.repository.SpecialistProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpecialistProfileService {
    private final SpecialistProfileRepository specialistProfileRepository;
    private final ThemeAndProfileRepository themeAndProfileRepository;
    private final SpecialistProfileMapper specialistProfileMapper;

    public SpecialistProfileService(final SpecialistProfileRepository specialistProfileRepository, final ThemeAndProfileRepository themeAndProfileRepository, final SpecialistProfileMapper specialistProfileMapper) {
        this.specialistProfileRepository = specialistProfileRepository;
        this.themeAndProfileRepository = themeAndProfileRepository;
        this.specialistProfileMapper = specialistProfileMapper;
    }

    @Transactional
    public com.example.psds.knowledge_base.dto.SpecialistProfile getSpecialistProfileByProfessionProfileId(Long specialistProfileId) {
        com.example.psds.knowledge_base.model.SpecialistProfile specialistProfileModel = specialistProfileRepository.findSpecialistProfileById(specialistProfileId);

        com.example.psds.knowledge_base.dto.SpecialistProfile specialistProfileObject = specialistProfileMapper.modelToObject(specialistProfileModel);
        return specialistProfileObject;
    }

}
