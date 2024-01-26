package com.example.psds.personal_account.dto;

import com.example.psds.personal_account.model.RoleInGroup;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Long id;
    private String lastName;
    private String firstName;
    private String fatherName;
    private String city;
    private String photo;
    private String phoneNumber;
    private String email;
    private String password;
    private List<RoleInGroup> roleInGroups = new ArrayList<>();
}
