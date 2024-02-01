package com.example.psds.knowledge_base.response;

import com.example.psds.knowledge_base.dto.PlanDTO;
import com.example.psds.knowledge_base.mapper.MapperPlan;
import com.example.psds.knowledge_base.model.Plan;
import com.example.psds.knowledge_base.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanResponseBuilder {
    private final PlanService planService;
    private final MapperPlan mapperPlan;

    public ResponseEntity<?> getPlanByRelationUsers(Long relationUsersId){

        Plan plan = planService.getPlanByRelationUsers(relationUsersId);
        PlanDTO planDTO = mapperPlan.modelToObject(plan);

        return new ResponseEntity<>(planDTO, HttpStatus.OK);
    }
}
