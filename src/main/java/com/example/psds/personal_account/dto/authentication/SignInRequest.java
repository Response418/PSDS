package com.example.psds.personal_account.dto.authentication;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {

    private String email;

    private String password;
}