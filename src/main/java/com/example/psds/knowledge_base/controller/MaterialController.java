package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.MaterialDTO;
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

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<MaterialDTO> getMaterialList(){
        return materialService.getMaterialList();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void changeMaterial(@RequestBody MaterialDTO materialDTO){
        materialService.changeMaterial(materialDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{materialId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMaterial(@PathVariable Long materialId){
        materialService.deleteMaterial(materialId);
    }
}
