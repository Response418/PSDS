package com.example.psds.knowledge_base.dto;

import com.example.psds.knowledge_base.model.Lesson;
import com.example.psds.knowledge_base.model.ThemeAndProfile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThemeDTO {
    private Long id;
    private String title;
    private String description;
    private List<LessonDTO> lessons;
}
