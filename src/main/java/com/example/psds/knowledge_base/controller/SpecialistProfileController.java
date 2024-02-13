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

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<SpecialistProfileDTO> getSpecialistProfileList(){
        return specialistProfileService.getSpecialistProfileList();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void changeSpecialistProfile(@RequestBody SpecialistProfileDTO specialistProfileDTO){
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
    }
    @RequestMapping(method = RequestMethod.DELETE, path = "/{specialistProfileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSpecialistProfile(@PathVariable Long specialistProfileId){
        specialistProfileService.deleteSpecialistProfile(specialistProfileId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{specialistProfileId}")
    @ResponseStatus(HttpStatus.OK)
    public SpecialistProfileDTO getSpecialistProfileById(@PathVariable Long specialistProfileId) {
        return specialistProfileService.getSpecialistProfileById(specialistProfileId);
    }
}
