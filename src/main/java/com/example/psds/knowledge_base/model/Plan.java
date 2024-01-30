package com.example.psds.knowledge_base.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
