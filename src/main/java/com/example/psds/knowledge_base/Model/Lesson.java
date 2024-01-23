package com.example.psds.knowledge_base.Model;

import jakarta.persistence.*;

@Entity
@Table(name ="t_lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private String difficult;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;
}
