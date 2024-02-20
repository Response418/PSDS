package com.example.psds.personal_account.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GroupDTO {
    private Long id;
    private String name;
    private String description;
    private List<String> userRoles;


    public GroupDTO(Long id, String name, String description, List<String> userRoles) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userRoles = userRoles;
    }
}
