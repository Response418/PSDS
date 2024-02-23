package com.example.psds.knowledge_base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonAndMaterialDTO {
    private LessonDTO lessonDTO;
    private List<MaterialDTO> materialDTO;
}
