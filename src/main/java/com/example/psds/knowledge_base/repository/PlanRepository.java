package com.example.psds.knowledge_base.repository;

import com.example.psds.knowledge_base.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findPlansByRelationUsersId(Long relationUsersId);
    Plan getByRelationUsersIdAndSpecialistProfile_Id(Long linkUsersId, Long specialistProfileId);
}
