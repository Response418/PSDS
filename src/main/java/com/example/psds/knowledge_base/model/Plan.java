package com.example.psds.knowledge_base.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "relation_users_id")
    private Long relationUsersId;

    @ManyToOne
    @JoinColumn(name = "specialist_profile_id")
    private SpecialistProfile specialistProfile;
}
