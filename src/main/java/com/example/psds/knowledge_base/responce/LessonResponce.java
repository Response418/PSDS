package com.example.psds.knowledge_base.responce;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LessonResponce {
    private Long id;
    private String title;
    private String description;
    private int level;
}
