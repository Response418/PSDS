package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.mapper.ModelSpecialistProfileAndObjectSpecialistProfile;
import com.example.psds.knowledge_base.mapper.ModelThemeAndObjectModel;
import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.model.Plan;
import com.example.psds.knowledge_base.model.ThemeAndProfile;
import com.example.psds.knowledge_base.repository.PlanRepository;
import com.example.psds.knowledge_base.dto.PlanDTO;
import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService {
    private final PlanRepository planRepository;
    private final ModelSpecialistProfileAndObjectSpecialistProfile modelSpecialistProfileAndObjectSpecialistProfile;
    private final ModelThemeAndObjectModel modelThemeAndObjectModel;

    public PlanService(final PlanRepository planRepository, final ModelSpecialistProfileAndObjectSpecialistProfile modelSpecialistProfileAndObjectSpecialistProfile, final ModelThemeAndObjectModel modelThemeAndObjectModel) {
        this.planRepository = planRepository;
        this.modelSpecialistProfileAndObjectSpecialistProfile = modelSpecialistProfileAndObjectSpecialistProfile;
        this.modelThemeAndObjectModel = modelThemeAndObjectModel;
    }

    @Transactional
    public PlanDTO getPlanByLinkUsersId(Long linkUsersId){
        List<Plan> planModels = planRepository.findPlansByRelationUsersId(linkUsersId);
        List<SpecialistProfileDTO> specialistProfiles = new ArrayList<>();
        List<ThemeAndProfile> themeAndProfiles;
        SpecialistProfile specialistProfile;
        for (int i=0; i<planModels.size(); i++){
            /*получаем профиль специалиста из плана*/
            specialistProfile = planModels.get(i).getSpecialistProfile();
            /*с помощью mapper*/
            specialistProfiles.add(modelSpecialistProfileAndObjectSpecialistProfile.modelToObject(specialistProfile));
            /*получаем связи*/
            themeAndProfiles = specialistProfile.getTapSpecialistProfile();

            for (int j=0; j<themeAndProfiles.size(); j++){
                specialistProfiles.get(i).getThemes().add(modelThemeAndObjectModel.modelToObject(themeAndProfiles.get(j).getTapTheme()));
            }
        }
        PlanDTO planDTO = new PlanDTO();
        planDTO.setRelationUsersId(linkUsersId);
        planDTO.setSpecialistProfiles(specialistProfiles);
        return planDTO;
    }

    @Transactional
    public void addSpecialistProfile(Long linkUsersId, SpecialistProfileDTO specialistProfileDTO){
        Plan plan = new Plan();
        plan.setRelationUsersId(linkUsersId);
        plan.setSpecialistProfile(modelSpecialistProfileAndObjectSpecialistProfile.objectToModel(specialistProfileDTO));
        planRepository.save(plan);
    }

    @Transactional
    public void deleteSpecialistProfile(Long linkUsersId, Long specialistProfileId){
        Plan plan = planRepository.getByRelationUsersIdAndSpecialistProfile_Id(linkUsersId, specialistProfileId);
        planRepository.delete(plan);
    }
}
