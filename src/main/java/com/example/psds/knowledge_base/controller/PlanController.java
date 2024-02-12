package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.LinkUsersDTO;
import com.example.psds.knowledge_base.service.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/plans")
public class PlanController {
    private final PlanService planService;

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlanBylinkUsers(@RequestBody LinkUsersDTO linkUsersDTO){
        planService.createPlanBylinkUsers(linkUsersDTO);
    }
}
