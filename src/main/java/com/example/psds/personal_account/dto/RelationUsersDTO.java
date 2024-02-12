package com.example.psds.personal_account.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RelationUsersDTO {
    private Long id;
    private GroupDTO group;
    private UserDTO student;
    private UserDTO master;
}
