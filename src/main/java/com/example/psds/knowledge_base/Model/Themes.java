package com.example.psds.knowledge_base.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "t_themes")
public class Themes {

    @Id
    @GeneratedValue
    @Column(name = "theme_id")
    private Long themeId;

    private String title;

    private String description;

    @Column(name = "specialist_profile_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tapSpecialistProfileId")
    private List<ThemesAndProfiles> specialistProfileId = new ArrayList<>();

    @Column(name = "lessons_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lessonId")
    private List<Lessons> lessonsId = new ArrayList<>();




}
