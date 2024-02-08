package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.MaterialDTO;
import com.example.psds.knowledge_base.service.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/materials")
public class MaterialController {
    private final MaterialService materialService;

    @RequestMapping(method = RequestMethod.GET, path = "/{lessonId}")
    @ResponseStatus(HttpStatus.OK)
    public List<MaterialDTO> getMaterialsByLessonId(@PathVariable Long lessonId){
        return materialService.getMaterialsByLessonId(lessonId);
    }
}
