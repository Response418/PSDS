package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.service.SpecialistProfileService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professionProfile")
public class SpecialistProfileController {
    private final SpecialistProfileService specialistProfileService;

    public SpecialistProfileController(final SpecialistProfileService specialistProfileService) {
        this.specialistProfileService = specialistProfileService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{professionProfileId}")
    public com.example.psds.knowledge_base.object.SpecialistProfile getSpecialistProfileByProfessionProfileId(@PathVariable Long professionProfileId){
        return specialistProfileService.getSpecialistProfileByProfessionProfileId(professionProfileId);
    }
}
