package com.example.psds.personal_account.model;


import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

}
