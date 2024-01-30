package com.example.psds.knowledge_base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {
    private Long id;
    private Long relationUsersId;
    private SpecialistProfileDTO specialistProfile;
}
