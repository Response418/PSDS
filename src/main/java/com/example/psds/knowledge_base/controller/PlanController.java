package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plan")
public class PlanController {
    private final PlanService planService;

    public PlanController(final PlanService planService) {
        this.planService = planService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{linkUsersId}")
    public com.example.psds.knowledge_base.dto.Plan getPlanByLinkUsersId (@PathVariable Long linkUsersId){
        return planService.getPlanByLinkUsersId(linkUsersId);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{planId}/specialistProfile")
    public ResponseEntity<String> addSpecialistProfile(@PathVariable Long planId, @RequestBody com.example.psds.knowledge_base.dto.SpecialistProfile specialistProfile) {
        planService.addSpecialistProfile(planId, specialistProfile);
        return new ResponseEntity<>("Successful add", HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{planId}/specialistProfile")
    public ResponseEntity<String> deleteSpecialistProfile(@PathVariable Long planId){
        planService.updateSpecialistProfile(planId);
        return new ResponseEntity<>("Successful delete", HttpStatus.NO_CONTENT);
    }
}
