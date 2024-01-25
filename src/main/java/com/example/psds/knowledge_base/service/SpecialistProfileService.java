package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.mapper.SpecialistProfileMapper;
import com.example.psds.knowledge_base.repository.SpecialistProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpecialistProfileService {
    private final SpecialistProfileRepository specialistProfileRepository;
    private final SpecialistProfileMapper specialistProfileMapper;

    public SpecialistProfileService(final SpecialistProfileRepository specialistProfileRepository, final SpecialistProfileMapper specialistProfileMapper) {
        this.specialistProfileRepository = specialistProfileRepository;
        this.specialistProfileMapper = specialistProfileMapper;
    }

    @Transactional
    public com.example.psds.knowledge_base.object.SpecialistProfile getSpecialistProfileByProfessionProfileId(Long professionProfileId){
        com.example.psds.knowledge_base.model.SpecialistProfile specialistProfileModel = specialistProfileRepository.findSpecialistProfileById(professionProfileId);
        return specialistProfileMapper.modelToObject(specialistProfileModel);
    }
}
