package com.example.psds.knowledge_base.repository;

import com.example.psds.knowledge_base.model.ThemeAndProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeAndProfileRepository extends JpaRepository<ThemeAndProfile, Long> {
    List<ThemeAndProfile> findTapSpecialistProfileById(Long specialistProfileId);
}
