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
@Table(name = "t_specialist_profile")
public class SpecialistProfile {

    @Id
    @GeneratedValue
    @Column(name = "specialist_profile_id")
    private Long specialistProfileId;

    private String title;

    private String description;

    @Column(name = "tap_specialist_profile")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tapSpecialistProfileId")
    private List<ThemesAndProfiles> tapSpecialistProfile = new ArrayList<>();

}
