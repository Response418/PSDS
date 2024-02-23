package com.example.psds.knowledge_base.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ThemeAndLessonAndMaterialsDTO {
    private Long themeId;
    private LessonDTO lessonDTO;
    private List<MaterialDTO> materialDTO;
}
