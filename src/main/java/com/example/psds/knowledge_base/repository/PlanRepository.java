package com.example.psds.knowledge_base.repository;

import com.example.psds.knowledge_base.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    Plan findPlanByRelationUsersId(Long relationUsersId);
    List<Plan> findPlansByRelationUsersId(Long relationUsersId);
    Plan getPlanByRelationUsersId(Long relationUsersId);
}
