package com.example.psds.knowledge_base.dto;

import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ThemeDTO {
    private Long id;
    private String title;
    private String description;
    private List<LessonDTO> lessons = new ArrayList<>();
}
