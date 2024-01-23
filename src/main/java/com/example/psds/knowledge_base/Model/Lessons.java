package com.example.psds.knowledge_base.Model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "t_lessons")
public class Lessons {
    @Id
    @GeneratedValue
    @Column(name = "lesson_id")
    private Long lessonId;

    private String title;

    private String description;

    private int level;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    private Themes theme;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Materials material;

}
