package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.MaterialDTO;
import com.example.psds.knowledge_base.dto.ThemeAndLessonAndMaterialsDTO;
import com.example.psds.knowledge_base.dto.ThemeDTO;
import com.example.psds.knowledge_base.model.Theme;
import com.example.psds.knowledge_base.repository.ThemeRepository;
import com.example.psds.knowledge_base.service.ThemeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/themes")
public class ThemeController {
    private final ThemeService themeService;
    private final ThemeRepository themeRepository;


    @GetMapping("")
    public ResponseEntity<?> getThemesList(){
        return new ResponseEntity<>(themeService.getThemeList(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void changeTheme(@RequestBody ThemeDTO themeDTO){
        Theme theme = themeService.saveTheme(themeDTO);
        for (int j=0; j<theme.getLessons().size(); j++){
            theme.getLessons().get(j).setTheme(theme);
            themeRepository.save(theme);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/lesson")
    public ResponseEntity<?> addLessonAndMaterialForTheme(@RequestBody ThemeAndLessonAndMaterialsDTO dto){
        themeService.addLessonAndMaterialForTheme(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{themeId}/{lessonId}")
    public ResponseEntity<?> deleteLessonFromTheme(@PathVariable Long themeId, @PathVariable Long lessonId){
        themeService.deleteLessonFromTheme(themeId, lessonId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{themeId}")
    public ResponseEntity<?> getThemeById(@PathVariable Long themeId) {
        return new ResponseEntity<>(themeService.getThemeById(themeId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{themeId}")
    public ResponseEntity<?> deleteTheme(@PathVariable Long themeId){
        themeService.deleteTheme(themeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
