package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import com.example.psds.knowledge_base.dto.ThemeDTO;
import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.model.Theme;
import com.example.psds.knowledge_base.service.SpecialistProfileService;
import com.example.psds.knowledge_base.service.ThemeAndProfileService;
import com.example.psds.knowledge_base.service.ThemeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/specialistProfiles")
public class SpecialistProfileController {
    private final SpecialistProfileService specialistProfileService;
    private final ThemeService themeService;
    private final ThemeAndProfileService themeAndProfileService;

    @GetMapping("")
    public ResponseEntity<?> getSpecialistProfileList(){
        return new ResponseEntity<>(specialistProfileService.getSpecialistProfileList(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("")
    public ResponseEntity<?> changeSpecialistProfile(@RequestBody SpecialistProfileDTO specialistProfileDTO){
        SpecialistProfile specialistProfile =  specialistProfileService.changeSpecialistProfile(specialistProfileDTO);
        List<ThemeDTO> themeDTOS = specialistProfileDTO.getThemes();
        List<Theme> themes = new ArrayList<>();
        Theme theme;
        for (int i=0; i< themeDTOS.size(); i++) {
            theme = themeService.saveTheme(themeDTOS.get(i));
            for (int j=0; j<theme.getLessons().size(); j++){
                theme.getLessons().get(j).setTheme(theme);
            }
            themes.add(theme);
        }
        themeAndProfileService.saveThemeAndProfileModels(specialistProfile, themes);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{specialistProfileId}")
    public ResponseEntity<?> deleteSpecialistProfile(@PathVariable Long specialistProfileId){
        specialistProfileService.deleteSpecialistProfile(specialistProfileId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{specialistProfileId}")
    public ResponseEntity<?> getSpecialistProfileById(@PathVariable Long specialistProfileId) {
        return new ResponseEntity<>(specialistProfileService.getSpecialistProfileById(specialistProfileId), HttpStatus.OK);
    }
}
