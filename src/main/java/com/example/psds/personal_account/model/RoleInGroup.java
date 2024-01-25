package com.example.psds.personal_account.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "t_role_in_group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleInGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "t_role",
            joinColumns = @JoinColumn(name = "role_in_group_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
}
