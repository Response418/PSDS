package com.example.psds.knowledge_base.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlanDTO {
    private Long relationUsersId;
    private List<SpecialistProfileDTO> specialistProfiles;
}
