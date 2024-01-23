package com.example.psds.knowledge_base.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_theme")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "specialist_profile_id")
    private SpecialistProfile specialistProfiles;

    @OneToMany(mappedBy = "theme")
    private List<Lesson> lessons;

}
