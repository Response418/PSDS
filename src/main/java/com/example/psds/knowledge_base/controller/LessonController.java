package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.LessonDTO;
import com.example.psds.knowledge_base.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    private final LessonService lessonService;

    public LessonController(final LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<LessonDTO> getLessonList(){
        return lessonService.getLessonList();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void changeLesson(LessonDTO lessonDTO){
        lessonService.changeLesson(lessonDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{lessonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLesson(@PathVariable Long lessonId){
        lessonService.deleteLesson(lessonId);
    }
}
