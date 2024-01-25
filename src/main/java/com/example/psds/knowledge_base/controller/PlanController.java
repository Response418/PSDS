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
    public com.example.psds.knowledge_base.object.Plan getPlanByLinkUsersId (@PathVariable Long linkUsersId){
        return planService.getPlanByLinkUsersId(linkUsersId);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{planId}")
    public ResponseEntity<String> addSpecialistProfile(@PathVariable Long planId, @RequestBody com.example.psds.knowledge_base.object.SpecialistProfile specialistProfile){
        planService.addSpecialistProfile(planId, specialistProfile);
        return new ResponseEntity<>("Successful add", HttpStatus.CREATED);
    }

    //что удаляем?
    @RequestMapping(method = RequestMethod.PUT, path = "/{planId}")
    public ResponseEntity<String> changeSpecialistProfile(@PathVariable Long planId, @RequestBody com.example.psds.knowledge_base.object.SpecialistProfile specialistProfile){
        planService.changeSpecialistProfile(planId, specialistProfile);
        return new ResponseEntity<>("Successful delete", HttpStatus.NO_CONTENT);
    }
}
