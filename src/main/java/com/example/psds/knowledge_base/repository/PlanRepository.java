package com.example.psds.knowledge_base.repository;

import com.example.psds.knowledge_base.Model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Long, Plan> {
    Plan findByRelationUsersId(Long relationUsersId);
    Plan findById(Long id);
}
