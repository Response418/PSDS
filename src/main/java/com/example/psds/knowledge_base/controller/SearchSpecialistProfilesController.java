package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import com.example.psds.knowledge_base.service.SpecialistProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/searchSpecialistProfiles")
public class SearchSpecialistProfilesController {
    private final SpecialistProfileService specialistProfileService;

    public SearchSpecialistProfilesController(SpecialistProfileService specialistProfileService) {
        this.specialistProfileService = specialistProfileService;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{searchString}")
    @ResponseStatus(HttpStatus.OK)
    public List<SpecialistProfileDTO> getSpecialistProfilesByString(@PathVariable String searchString){
        return specialistProfileService.getSpecialistProfilesByString(searchString);
    }
}
