package com.example.psds.knowledge_base.Model;

import com.example.psds.personal_account.Model.RelationUsers;
import jakarta.persistence.*;

@Entity
@Table(name = "t_grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int grade;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "relation_users_id")
    private RelationUsers relationUsers;
}
