package com.example.psds.knowledge_base.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Lesson {
    private Long id;
    private String title;
    private String description;
    private int level;
    private List<Material> materials = new ArrayList<>();
    private Theme theme;
}
