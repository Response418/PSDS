package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import com.example.psds.knowledge_base.service.SpecialistProfileService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/specialistProfile")
public class SpecialistProfileController {
    private final SpecialistProfileService specialistProfileService;

    public SpecialistProfileController(final SpecialistProfileService specialistProfileService) {
        this.specialistProfileService = specialistProfileService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{specialistProfileId}")
    public SpecialistProfileDTO getSpecialistProfileBySpecialistProfileId(@PathVariable Long specialistProfileId){
        return specialistProfileService.getSpecialistProfileByProfessionProfileId(specialistProfileId);
    }
}
