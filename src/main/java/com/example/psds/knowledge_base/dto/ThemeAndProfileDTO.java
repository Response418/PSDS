package com.example.psds.knowledge_base.dto;

import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.model.Theme;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ThemeAndProfileDTO {
    private Long id;
    private Theme tapTheme;
    private SpecialistProfile tapSpecialistProfileId;
}
