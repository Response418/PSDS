package com.example.psds.knowledge_base.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_specialist_profile")
public class SpecialistProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @OneToMany(mappedBy = "specialistProfiles")
    private List<Theme> themes;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;
}
