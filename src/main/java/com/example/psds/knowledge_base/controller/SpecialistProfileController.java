package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.service.SpecialistProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ProfessionProfile", description = "Управление профилями специалиста")
@RestController
@RequestMapping("/specialistProfiles")
public class SpecialistProfileController {
    @Autowired
    private SpecialistProfileService specialistProfileService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<SpecialistProfile> getSpecialistProfileList(){
        return specialistProfileService.getSpecialistProfileList();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createSpecialistProfile(@RequestBody SpecialistProfile specialistProfile){
        specialistProfileService.createSpecialistProfile(specialistProfile);
    }
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSpecialistProfile(@RequestBody SpecialistProfile specialistProfile){
        specialistProfileService.updateSpecialistProfile(specialistProfile);
    }

    @RequestMapping(method = RequestMethod.GET, path = "professionProfileId")
    @ResponseStatus(HttpStatus.OK)
    public SpecialistProfile getSpecialistProfileById(@PathVariable Long specialistProfileId) {
        return specialistProfileService.getSpecialistProfileById(specialistProfileId);
    }
}
