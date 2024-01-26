package com.example.psds.knowledge_base.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SpecialistProfile {
    private long id;
    private String title;
    private String description;
}
