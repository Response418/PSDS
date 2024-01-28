package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import com.example.psds.knowledge_base.dto.PlanDTO;
import com.example.psds.knowledge_base.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plan")
public class PlanController {
    private final PlanService planService;

    public PlanController(final PlanService planService) {
        this.planService = planService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{linkUsersId}")
    @ResponseStatus(HttpStatus.OK)
    public PlanDTO getPlanByLinkUsersId (@PathVariable Long linkUsersId){
        return planService.getPlanByLinkUsersId(linkUsersId);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{linkUsersId}/specialistProfile")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addSpecialistProfile(@PathVariable Long linkUsersId, @RequestBody SpecialistProfileDTO specialistProfileDTO) {
        planService.addSpecialistProfile(linkUsersId, specialistProfileDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{linkUsersId}/specialistProfile/{specialistProfileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSpecialistProfile(@PathVariable Long linkUsersId, @PathVariable Long specialistProfileId){
        planService.deleteSpecialistProfile(linkUsersId,specialistProfileId);
    }
}
