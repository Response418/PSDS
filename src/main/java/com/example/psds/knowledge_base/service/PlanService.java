package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.model.Plan;
import com.example.psds.knowledge_base.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;

    public Plan getPlanByRelationUsers(Long relationUsersId){
        return planRepository.getPlanByRelationUsersId(relationUsersId);
    }
}
