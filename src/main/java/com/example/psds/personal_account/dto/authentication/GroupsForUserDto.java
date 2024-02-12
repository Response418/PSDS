package com.example.psds.personal_account.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GroupsForUserDto {
    private String id;
    private String name;
    private String description;
}
