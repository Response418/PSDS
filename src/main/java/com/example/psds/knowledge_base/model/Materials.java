package com.example.psds.knowledge_base.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "t_materials")
public class Materials {

    @Id
    @GeneratedValue
    @Column(name = "material_id")
    private Long materialId;

    private String title;

    private String description;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lessons lesson;


}