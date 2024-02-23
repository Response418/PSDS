package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.LessonAndMaterialDTO;
import com.example.psds.knowledge_base.dto.LessonDTO;
import com.example.psds.knowledge_base.response.LessonResponseBuilder;
import com.example.psds.knowledge_base.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/lesson")
    public ResponseEntity<?> editLessonsAndMaterial(@RequestBody LessonAndMaterialDTO dto) {
        lessonService.editLessonsAndMaterial(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<LessonDTO> getLessonList(){
        return lessonService.getLessonList();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void changeLesson(@RequestBody LessonDTO lessonDTO){
        lessonService.changeLesson(lessonDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{lessonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLesson(@PathVariable Long lessonId){
        lessonService.deleteLesson(lessonId);
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
