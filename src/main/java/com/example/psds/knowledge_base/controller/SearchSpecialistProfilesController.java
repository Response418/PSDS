package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import com.example.psds.knowledge_base.service.SpecialistProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/searchSpecialistProfiles")
@AllArgsConstructor
public class SearchSpecialistProfilesController {

    private final SpecialistProfileService specialistProfileService;

    @GetMapping("/{searchString}")
    public ResponseEntity<?> getSpecialistProfilesByString(@PathVariable String searchString){
        return new ResponseEntity<>(specialistProfileService.getSpecialistProfilesByString(searchString), HttpStatus.OK);
    }
}
