package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.mapper.PlanMapper;
import com.example.psds.knowledge_base.mapper.SpecialistProfileMapper;
import com.example.psds.knowledge_base.repository.PlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlanService {
    private final PlanRepository planRepository;
    private final PlanMapper planMapper;
    private final SpecialistProfileMapper specialistProfileMapper;

    public PlanService(final PlanRepository planRepository, final PlanMapper planMapper, final SpecialistProfileMapper specialistProfileMapper) {
        this.planRepository = planRepository;
        this.planMapper = planMapper;
        this.specialistProfileMapper = specialistProfileMapper;
    }

    @Transactional
    public com.example.psds.knowledge_base.dto.Plan getPlanByLinkUsersId(Long linkUsersId){
        com.example.psds.knowledge_base.model.Plan planModel = planRepository.findPlanByRelationUsersId(linkUsersId);
        return planMapper.modelToObject(planModel);
    }

    @Transactional
    public void addSpecialistProfile(Long planId, com.example.psds.knowledge_base.dto.SpecialistProfile specialistProfile){
        com.example.psds.knowledge_base.model.SpecialistProfile specialistProfileModel = specialistProfileMapper.objectToModel(specialistProfile);
        com.example.psds.knowledge_base.model.Plan plan = planRepository.findPlanById(planId);
        plan.setSpecialistProfile(specialistProfileModel);
        planRepository.save(plan);
    }

    @Transactional
    public void updateSpecialistProfile(Long planId){
        com.example.psds.knowledge_base.model.Plan plan = planRepository.findPlanById(planId);
        plan.setSpecialistProfile(null);//?
        planRepository.save(plan);
    }
}
