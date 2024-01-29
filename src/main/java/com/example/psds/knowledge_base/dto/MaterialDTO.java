package com.example.psds.knowledge_base.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MaterialDTO {
    private Long id;
    private String title;
    private String description;
    private LessonDTO lesson;
}