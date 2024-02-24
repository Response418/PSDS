package com.example.psds.personal_account.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_session")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionId;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne()
    @JoinColumn(name = "group_id")
    private Group group;


    private LocalDateTime dateOfCreated;


}
