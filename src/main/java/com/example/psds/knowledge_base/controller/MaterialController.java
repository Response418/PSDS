package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.responce.MaterialResponce;
import com.example.psds.knowledge_base.service.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {
    private final MaterialService materialService;

    public MaterialController(final MaterialService materialService) {
        this.materialService = materialService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{lessonId}")
    @ResponseStatus(HttpStatus.OK)
    public List<MaterialResponce> getMaterialsByLessonId(@PathVariable Long lessonId){
        return materialService.getMaterialsByLessonId(lessonId);
    }
}
