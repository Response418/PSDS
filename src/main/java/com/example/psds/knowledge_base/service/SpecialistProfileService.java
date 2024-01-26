package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.repository.SpecialistProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialistProfileService {
    @Autowired
    private SpecialistProfileRepository specialistProfileRepository;

    public List<SpecialistProfile> getSpecialistProfileList(){
        return specialistProfileRepository.findAll();
    }

    public void createSpecialistProfile(SpecialistProfile specialistProfile){
        specialistProfileRepository.save(specialistProfile);
    }

    public void updateSpecialistProfile(SpecialistProfile specialistProfile){
        specialistProfileRepository.save(specialistProfile);
    }

    public SpecialistProfile getSpecialistProfileById(Long specialistProfileId){
        return specialistProfileRepository.findSpecialistProfilesById(specialistProfileId);
    }
}
