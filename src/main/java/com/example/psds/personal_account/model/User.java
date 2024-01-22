package com.example.psds.personal_account.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastName;
    private String firstName;
    private String fatherName;
    private String city;
    private String photo;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<RoleInGroup> roleInGroups = new ArrayList<>();



}