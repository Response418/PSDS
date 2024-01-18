package com.example.psds.knowledge_base.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_themes")
public class Themes {

    @Id
    @GeneratedValue
    @Column(name = "theme_id")
    public Long themeId;

    int title;

    int description;

}
