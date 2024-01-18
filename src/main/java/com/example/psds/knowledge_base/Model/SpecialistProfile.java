package com.example.psds.knowledge_base.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "t_specialist_profile")
public class SpecialistProfile {

    @Id
    @GeneratedValue
    @Column(name = "specialist_profile_id")
    public Long specialistProfileId;

    int title;

    int description;

    @OneToMany(mappedBy = "themeId")
    public List<Themes> themes;

}
