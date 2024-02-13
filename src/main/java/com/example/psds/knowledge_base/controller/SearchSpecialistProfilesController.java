package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import com.example.psds.knowledge_base.service.SpecialistProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/searchSpecialistProfiles")
@AllArgsConstructor
public class SearchSpecialistProfilesController {

    private final SpecialistProfileService specialistProfileService;

    @RequestMapping(method = RequestMethod.GET, path = "/{searchString}")
    @ResponseStatus(HttpStatus.OK)
    public List<SpecialistProfileDTO> getSpecialistProfilesByString(@PathVariable String searchString){
        return specialistProfileService.getSpecialistProfilesByString(searchString);
    }
}
