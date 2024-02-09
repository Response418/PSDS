package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.dto.LinkUsersDTO;
import com.example.psds.knowledge_base.model.Plan;
import com.example.psds.knowledge_base.repository.PlanRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class PlanService {
    private final PlanRepository planRepository;

    public void createPlanBylinkUsers(@NotNull LinkUsersDTO linkUsersDTO){
        Plan plan = planRepository.findPlanByRelationUsersId(linkUsersDTO.getLinkUsersId());
        if (plan==null) {
            plan = new Plan();
            plan.setRelationUsersId(linkUsersDTO.getLinkUsersId());
            planRepository.save(plan);
        }
    }

}
