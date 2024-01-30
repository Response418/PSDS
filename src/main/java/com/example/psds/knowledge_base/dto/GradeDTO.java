package com.example.psds.knowledge_base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
    private Long id;
    private int grade;
    private LessonDTO lesson;
    private Long relationUsersId;
}
