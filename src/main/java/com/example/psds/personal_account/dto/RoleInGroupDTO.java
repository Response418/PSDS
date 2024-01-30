package com.example.psds.personal_account.dto;

import com.example.psds.personal_account.model.Role;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleInGroupDTO {
    private Long id;
    private UserDTO user;
    private GroupDTO group;
    private Set<Role> roles = new HashSet<>();
}
