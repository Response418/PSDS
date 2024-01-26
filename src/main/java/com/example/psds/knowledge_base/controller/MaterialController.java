package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.service.MaterialService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {
    private final MaterialService materialService;

    public MaterialController(final MaterialService materialService) {
        this.materialService = materialService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{lessonId}")
    public List<com.example.psds.knowledge_base.dto.Material> getMaterialsByLessonId(@PathVariable Long lessonId){
        return materialService.getMaterialsByLessonId(lessonId);
    }
}
