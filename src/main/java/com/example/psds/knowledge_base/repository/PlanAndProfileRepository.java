package com.example.psds.knowledge_base.repository;

import com.example.psds.knowledge_base.model.Plan;
import com.example.psds.knowledge_base.model.PlanAndProfile;
import com.example.psds.knowledge_base.model.SpecialistProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanAndProfileRepository extends JpaRepository<PlanAndProfile, Long> {
    void deleteByPlanAndSpecialistProfile(Plan plan, SpecialistProfile specialistProfile);

    List<PlanAndProfile> findAllByPlan(Plan plan);
}
