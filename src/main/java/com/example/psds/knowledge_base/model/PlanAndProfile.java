package com.example.psds.knowledge_base.model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "t_plan_and_profile")
public class PlanAndProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "specialist_profile_id")
    private SpecialistProfile specialistProfile;
}
