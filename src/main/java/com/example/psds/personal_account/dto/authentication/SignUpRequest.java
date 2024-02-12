package com.example.psds.personal_account.dto.authentication;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String lastName;
    private String firstName;
    private String fatherName;
    private String city;
    private String phoneNumber;
    private String email;
    private String password;
}