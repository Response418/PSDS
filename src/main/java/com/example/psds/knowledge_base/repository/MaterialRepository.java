package com.example.psds.knowledge_base.repository;

import com.example.psds.knowledge_base.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findMaterialsByLesson_Id(Long lessonId);
}
