package com.example.psds.knowledge_base.repository;

import com.example.psds.knowledge_base.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    Plan getPlanByRelationUsersId(Long relationUsersId);
}
