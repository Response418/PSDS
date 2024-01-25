package com.example.psds.knowledge_base.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "t_themes_and_profiles")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ThemeAndProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    private Theme tapTheme;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "specialist_profile_id")
    private SpecialistProfile tapSpecialistProfileId;

}
