package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.mapper.PlanMapper;
import com.example.psds.knowledge_base.repository.PlanRepository;
import com.example.psds.knowledge_base.repository.SpecialistProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlanService {
    private final PlanRepository planRepository;
    private final SpecialistProfileRepository specialistProfileRepository;
    private final PlanMapper planMapper;

    public PlanService(final PlanRepository planRepository, final SpecialistProfileRepository specialistProfileRepository, final PlanMapper planMapper) {
        this.planRepository = planRepository;
        this.specialistProfileRepository = specialistProfileRepository;
        this.planMapper = planMapper;
    }

    @Transactional
    public com.example.psds.knowledge_base.object.Plan getPlanByLinkUsersId(Long linkUsersId){
        com.example.psds.knowledge_base.Model.Plan planModel = planRepository.findByRelationUsersId(linkUsersId);
        return planMapper.modelToObject(planModel);
    }

    @Transactional
    public void addSpecialistProfile(Long planId, Long professionProfileId){
        com.example.psds.knowledge_base.Model.SpecialistProfile specialistProfile = specialistProfileRepository.findById(professionProfileId);
        com.example.psds.knowledge_base.Model.Plan plan = planRepository.findById(planId);
        plan.setSpecialistProfile(specialistProfile);
        //planRepository.save(plan);
    }
}
