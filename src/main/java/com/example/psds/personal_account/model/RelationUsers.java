package com.example.psds.personal_account.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_relation_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RelationUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private User master;

}