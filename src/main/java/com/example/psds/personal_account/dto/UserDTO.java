package com.example.psds.personal_account.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private String fatherName;
    private String city;
    private String photo;
    private String phoneNumber;
    private String email;
    private String password;
}
