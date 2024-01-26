package com.example.psds.knowledge_base.responce;

import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ThemeResponse {
    private Long id;
    private String title;
    private String description;
    private List<LessonResponce> lessonResponces = new ArrayList<>();
}
