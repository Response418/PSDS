package com.example.psds.knowledge_base.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Material {
    private Long id;
    private String title;
    private String description;
    private Lesson lesson;
}
