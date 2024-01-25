package com.example.psds.personal_account.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_moderator")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Moderator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
}
