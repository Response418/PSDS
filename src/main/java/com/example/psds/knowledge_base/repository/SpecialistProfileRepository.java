package com.example.psds.knowledge_base.repository;

import com.example.psds.knowledge_base.model.SpecialistProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialistProfileRepository extends JpaRepository<SpecialistProfile, Long> {
    SpecialistProfile findSpecialistProfileById(Long specialistProfileId);
    List<SpecialistProfile> findByTitleContainingIgnoreCase(String title);

}
