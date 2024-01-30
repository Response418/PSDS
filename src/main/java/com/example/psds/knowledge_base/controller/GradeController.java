package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.GradeRequestDTO;
import com.example.psds.knowledge_base.response.GradeResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/grade")
public class GradeController {
    private final GradeResponseBuilder gradeResponseBuilder;

    @PutMapping("/{lessonId}/{relationUsersId}")
    public ResponseEntity<?> updateGrade(
            @PathVariable("relationUsersId") Long relationUsersId,
            @PathVariable("lessonId") Long lessonId,
            @RequestBody GradeRequestDTO gradeRequestDTO
            ) {
        return gradeResponseBuilder.updateGrade(lessonId, relationUsersId, gradeRequestDTO);
    }
}
