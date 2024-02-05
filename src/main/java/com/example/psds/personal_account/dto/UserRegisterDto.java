package com.example.psds.personal_account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto {
    private String lastName;
    private String firstName;
    private String fatherName;
    private String city;
    private String phoneNumber;
    private String email;
    private String password;
}
