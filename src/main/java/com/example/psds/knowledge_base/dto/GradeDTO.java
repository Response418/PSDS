package com.example.psds.knowledge_base.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GradeDTO {
    private Long id;

    private int value;

    private LessonDTO lesson;
    private Long relationUsersId;
}
