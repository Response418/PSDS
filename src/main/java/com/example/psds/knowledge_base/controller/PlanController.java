package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plan")
public class PlanController {
    private final PlanService planService;

    public PlanController(final PlanService planService) {
        this.planService = planService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{linkUsersId}")
    public com.example.psds.knowledge_base.object.Plan getPlanByLinkUsersId (@PathVariable Long linkUsersId){
        return planService.getPlanByLinkUsersId(linkUsersId);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{planId}/{professionProfileId}")
    public ResponseEntity<String> addSpecialistProfile(@PathVariable Long planId, @PathVariable Long professionProfileId){
        try {
            planService.addSpecialistProfile(planId, professionProfileId);
            return new ResponseEntity<>("Successful added", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
