package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.MaterialDTO;
import com.example.psds.knowledge_base.service.LessonService;
import com.example.psds.knowledge_base.service.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/materials")
public class MaterialController {
    private final MaterialService materialService;
    private final LessonService lessonService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<MaterialDTO> getMaterialList(){
        return materialService.getMaterialList();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> changeMaterial(@RequestBody MaterialDTO materialDTO){
        materialService.changeMaterial(materialDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{materialId}")
    public ResponseEntity<?> deleteMaterial(@PathVariable Long materialId) {
        lessonService.changeLessonByMaterialId(materialId);
        materialService.deleteMaterial(materialId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(method = RequestMethod.GET, path = "/{lessonId}")
    @ResponseStatus(HttpStatus.OK)
    public List<MaterialDTO> getMaterialsByLessonId(@PathVariable Long lessonId){
        return materialService.getMaterialsByLessonId(lessonId);
    }
}
