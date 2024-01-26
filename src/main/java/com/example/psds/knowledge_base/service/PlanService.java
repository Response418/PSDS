package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.model.Lesson;
import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.model.Plan;
import com.example.psds.knowledge_base.model.ThemeAndProfile;
import com.example.psds.knowledge_base.repository.PlanRepository;
import com.example.psds.knowledge_base.responce.LessonResponce;
import com.example.psds.knowledge_base.responce.PlanResponce;
import com.example.psds.knowledge_base.responce.SpecialistProfileResponce;
import com.example.psds.knowledge_base.responce.ThemeResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService {
    private final PlanRepository planRepository;

    public PlanService(final PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Transactional
    public PlanResponce getPlanByLinkUsersId(Long linkUsersId){
        List<Plan> planModels = planRepository.findPlansByRelationUsersId(linkUsersId);
        List<SpecialistProfileResponce> specialistProfiles = new ArrayList<>();
        List<ThemeAndProfile> themeAndProfiles;
        List<Lesson> lessons;
        SpecialistProfile specialistProfile;
        for (int i=0; i<planModels.size(); i++){
            specialistProfile = planModels.get(i).getSpecialistProfile();
            specialistProfiles.add(i, new SpecialistProfileResponce());
            specialistProfiles.get(i).setId(specialistProfile.getId());
            specialistProfiles.get(i).setTitle(specialistProfile.getTitle());
            specialistProfiles.get(i).setDescription(specialistProfile.getDescription());

            themeAndProfiles = specialistProfile.getTapSpecialistProfile();

            for (int j=0; j<themeAndProfiles.size(); j++){
                specialistProfiles.get(i).getThemeResponses().add(j, new ThemeResponse());
                specialistProfiles.get(i).getThemeResponses().get(j).setId(themeAndProfiles.get(j).getTapTheme().getId());
                specialistProfiles.get(i).getThemeResponses().get(j).setTitle(themeAndProfiles.get(j).getTapTheme().getTitle());
                specialistProfiles.get(i).getThemeResponses().get(j).setDescription(themeAndProfiles.get(j).getTapTheme().getDescription());

                lessons = themeAndProfiles.get(j).getTapTheme().getLessons();

                for (int k=0; k<lessons.size(); k++){
                    specialistProfiles.get(i).getThemeResponses().get(j).getLessonResponces().add(k, new LessonResponce());
                    specialistProfiles.get(i).getThemeResponses().get(j).getLessonResponces().get(k).setId(lessons.get(k).getId());
                    specialistProfiles.get(i).getThemeResponses().get(j).getLessonResponces().get(k).setTitle(lessons.get(k).getTitle());
                    specialistProfiles.get(i).getThemeResponses().get(j).getLessonResponces().get(k).setDescription(lessons.get(k).getDescription());
                    specialistProfiles.get(i).getThemeResponses().get(j).getLessonResponces().get(k).setLevel(lessons.get(k).getLevel());
                }
            }
        }
        PlanResponce planResponce = new PlanResponce();
        planResponce.setRelationUsersId(linkUsersId);
        planResponce.setSpecialistProfiles(specialistProfiles);
        return planResponce;
    }

    @Transactional
    public void addSpecialistProfile(Long linkUsersId, SpecialistProfile specialistProfile){
        Plan plan = new Plan();
        plan.setRelationUsersId(linkUsersId);
        plan.setSpecialistProfile(specialistProfile);
        planRepository.save(plan);
    }

    @Transactional
    public void deleteSpecialistProfile(Long linkUsersId, Long specialistProfileId){
        com.example.psds.knowledge_base.model.Plan plan = planRepository.getByRelationUsersIdAndSpecialistProfile_Id(linkUsersId, specialistProfileId);
        planRepository.delete(plan);
    }
}
