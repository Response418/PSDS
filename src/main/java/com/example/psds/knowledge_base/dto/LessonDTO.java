package com.example.psds.knowledge_base.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LessonDTO {
    private Long id;
    private String title;
    private String description;
    private int level;
    private int grade;
}
