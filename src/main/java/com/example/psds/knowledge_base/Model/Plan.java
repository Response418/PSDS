package com.example.psds.knowledge_base.Model;

import com.example.psds.personal_account.Model.RelationUsers;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "relation_user_id")
    private RelationUsers relationUsers;

    @OneToMany(mappedBy = "plan")
    private List<SpecialistProfile> specialistProfile;
}
