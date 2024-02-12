package com.example.psds.personal_account.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private String fatherName;
    private String city;
    private Long linkUserId;
}
