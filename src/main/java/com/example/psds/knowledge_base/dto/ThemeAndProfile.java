package com.example.psds.knowledge_base.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ThemeAndProfile {
    private long id;
    private SpecialistProfile specialistProfile;
    private Theme theme;
}
