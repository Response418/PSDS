package com.example.psds.knowledge_base.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_grade")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Column(name = "relation_users_id")
    private Long relationUsersId;
}
