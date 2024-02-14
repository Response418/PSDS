package com.example.psds.knowledge_base.repository;

import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.model.Theme;
import com.example.psds.knowledge_base.model.ThemeAndProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThemeAndProfileRepository extends JpaRepository<ThemeAndProfile, Long> {
    List<ThemeAndProfile> findByTapSpecialistProfileId(SpecialistProfile specialistProfile);
}
