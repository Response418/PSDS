package com.example.psds.knowledge_base.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_plan")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "relation_users_id")
    private Long relationUsersId;
}
