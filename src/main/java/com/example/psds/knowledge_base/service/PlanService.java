package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.dto.LinkUsersDTO;
import com.example.psds.knowledge_base.mapper.ModelSpecialistProfileAndObjectSpecialistProfile;
import com.example.psds.knowledge_base.mapper.ModelThemeAndObjectModel;
import com.example.psds.knowledge_base.model.Plan;
import com.example.psds.knowledge_base.model.PlanAndProfile;
import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.repository.PlanAndProfileRepository;
import com.example.psds.knowledge_base.repository.PlanRepository;
import com.example.psds.knowledge_base.repository.SpecialistProfileRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import com.example.psds.knowledge_base.dto.PlanDTO;
import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class PlanService {
    private final PlanRepository planRepository;
    private final ModelSpecialistProfileAndObjectSpecialistProfile modelSpecialistProfileAndObjectSpecialistProfile;
    private final ModelThemeAndObjectModel modelThemeAndObjectModel;
    private final PlanAndProfileRepository planAndProfileRepository;
    private final SpecialistProfileRepository specialistProfileRepository;


    public void createPlan(Plan plan){
        planRepository.save(plan);
    }

    public PlanDTO getPlanByLinkUsersId(Long linkUsersId){
        Plan plan = planRepository.findPlanByRelationUsersId(linkUsersId);
        List<PlanAndProfile> pap = plan.getPlanAndProfiles();
        List<SpecialistProfileDTO> specialistProfileDTOS =
                pap.stream()
                    .map(PlanAndProfile::getSpecialistProfile)
                    .map(modelSpecialistProfileAndObjectSpecialistProfile::modelToObject)
                    .toList();
        return new PlanDTO(
                linkUsersId,
                specialistProfileDTOS
            );
    }

    public void addSpecialistProfile(Long linkUsersId, @NotNull SpecialistProfileDTO specialistProfileDTO){
        Plan plan = planRepository.getPlanByRelationUsersId(linkUsersId);
        SpecialistProfile specialistProfile =
                specialistProfileRepository
                        .findSpecialistProfilesById(
                                specialistProfileDTO.getId()
                        );

        PlanAndProfile planAndProfile = new PlanAndProfile();
        planAndProfile.setSpecialistProfile(specialistProfile);
        planAndProfile.setPlan(plan);

        planAndProfileRepository.save(planAndProfile);
    }

    public void createPlanBylinkUsers(@NotNull LinkUsersDTO linkUsersDTO){
        Plan plan = planRepository.findPlanByRelationUsersId(linkUsersDTO.getLinkUsersId());
        if (plan==null) {
            plan = new Plan();
            plan.setRelationUsersId(linkUsersDTO.getLinkUsersId());
            planRepository.save(plan);
        }
    }
    @Transactional
    public void deleteSpecialistProfile(Long linkUsersId, Long specialistProfileId){
        Plan plan = planRepository.getPlanByRelationUsersId(linkUsersId);
        SpecialistProfile specialistProfile = specialistProfileRepository.findSpecialistProfilesById(specialistProfileId);
        planAndProfileRepository.deleteByPlanAndSpecialistProfile(plan, specialistProfile);
    }

    public Plan getPlanByRelationUsers(Long linkUserId){
        return planRepository.getPlanByRelationUsersId(linkUserId);
    }
}
