package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.response.PlanResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan")
public class PlanController {

    private final PlanResponseBuilder planResponseBuilder;

    @GetMapping("/{relationUsersId}")
    public ResponseEntity<?> getPlanByRelationUsers(
            @PathVariable("relationUsersId") Long relationUsersId
    ) {
        return planResponseBuilder.getPlanByRelationUsers(relationUsersId);
    }

}