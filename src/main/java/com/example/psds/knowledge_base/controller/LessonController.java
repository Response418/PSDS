package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.LessonAndMaterialDTO;
import com.example.psds.knowledge_base.dto.LessonDTO;
import com.example.psds.knowledge_base.response.LessonResponseBuilder;
import com.example.psds.knowledge_base.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/lessons")
public class LessonController {
    private final LessonService lessonService;
    private final LessonResponseBuilder lessonResponseBuilder;

    @GetMapping("/lesson/{themeId}")
    public ResponseEntity<?> getLessonsForTheme(@PathVariable Long themeId) {
        return new ResponseEntity<>(lessonService.getLessonsForTheme(themeId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/lesson")
    public ResponseEntity<?> editLessonsAndMaterial(@RequestBody LessonAndMaterialDTO dto) {
        lessonService.editLessonsAndMaterial(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> getLessonList(){
        return new ResponseEntity<>(lessonService.getLessonList(), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("")
    public ResponseEntity<?> changeLesson(@RequestBody LessonDTO lessonDTO){
        lessonService.changeLesson(lessonDTO);
        return new ResponseEntity<>(lessonService.getLessonList(), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{lessonId}")
    public ResponseEntity<?> deleteLesson(@PathVariable Long lessonId){
        lessonService.deleteLesson(lessonId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{lessonId}/{linkUserId}")
    public ResponseEntity<?> getLessonByIdAndRelationUsers(
            @PathVariable("linkUserId") Long linkUserId,
            @PathVariable("lessonId") Long lessonId
    ) {
        return lessonResponseBuilder.getLessonByIdAndRelationUsers(lessonId, linkUserId);
    }

    @GetMapping("/{lessonId}")
    public LessonDTO getLessonByIdAndRelationUsers(
            @PathVariable("lessonId") Long lessonId
    ) {
        return lessonService.getLessonById(lessonId);
    }
}
