package com.example.psds.knowledge_base.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Plan {
    private Long id;
    private Long relationUsersId;
    private SpecialistProfile specialistProfile;
}
