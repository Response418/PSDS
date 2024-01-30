package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.response.LessonResponseBuilder;
import com.example.psds.knowledge_base.response.PlanResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lesson")
public class LessonController {

    private final LessonResponseBuilder lessonResponseBuilder;

    @GetMapping("/{lessonId}/{relationUsersId}")
    public ResponseEntity<?> getLessonByIdAndRelationUsers(
            @PathVariable("relationUsersId") Long relationUsersId,
            @PathVariable("lessonId") Long lessonId
    ) {
        return lessonResponseBuilder.getLessonByIdAndRelationUsers(lessonId, relationUsersId);
    }

}