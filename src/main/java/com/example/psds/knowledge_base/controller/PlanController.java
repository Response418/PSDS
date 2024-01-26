package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.responce.PlanResponce;
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
    public PlanResponce getPlanByLinkUsersId (@PathVariable Long linkUsersId){
        return planService.getPlanByLinkUsersId(linkUsersId);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{linkUsersId}/specialistProfile")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addSpecialistProfile(@PathVariable Long linkUsersId, @RequestBody SpecialistProfile specialistProfile) {
        planService.addSpecialistProfile(linkUsersId, specialistProfile);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{linkUsersId}/specialistProfile/{specialistProfileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSpecialistProfile(@PathVariable Long linkUsersId, @PathVariable Long specialistProfileId){
        planService.deleteSpecialistProfile(linkUsersId,specialistProfileId);
    }
}
