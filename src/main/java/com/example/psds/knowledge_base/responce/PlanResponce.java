package com.example.psds.knowledge_base.responce;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlanResponce {
    private Long relationUsersId;
    private List<SpecialistProfileResponce> specialistProfiles;
}
