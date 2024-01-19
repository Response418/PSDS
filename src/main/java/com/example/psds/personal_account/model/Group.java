package com.example.psds.personal_account.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
    private List<RoleInGroup> roleInGroups = new ArrayList<>();
}
