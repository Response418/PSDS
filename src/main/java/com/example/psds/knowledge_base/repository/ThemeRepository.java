package com.example.psds.knowledge_base.repository;

import com.example.psds.knowledge_base.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme, Long> {

    Theme findThemeById(Long themeId);
//    List<Theme> findThemeById(Long themeId);

}
