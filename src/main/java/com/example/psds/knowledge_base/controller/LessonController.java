package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.response.LessonResponseBuilder;
import com.example.psds.knowledge_base.response.PlanResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonResponseBuilder lessonResponseBuilder;

    @GetMapping("/{lessonId}/{linkUserId}")
    public ResponseEntity<?> getLessonByIdAndRelationUsers(
            @PathVariable("linkUserId") Long linkUserId,
            @PathVariable("lessonId") Long lessonId
    ) {
        return lessonResponseBuilder.getLessonByIdAndRelationUsers(lessonId, linkUserId);
    }

}