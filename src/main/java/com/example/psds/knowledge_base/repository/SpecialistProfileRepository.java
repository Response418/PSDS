package com.example.psds.knowledge_base.repository;

import com.example.psds.knowledge_base.Model.SpecialistProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialistProfileRepository extends JpaRepository<Long, SpecialistProfile> {
    SpecialistProfile findById(Long id);
}
