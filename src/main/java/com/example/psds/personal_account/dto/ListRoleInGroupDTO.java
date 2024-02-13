package com.example.psds.personal_account.dto;

import com.example.psds.personal_account.model.Group;
import com.example.psds.personal_account.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListRoleInGroupDTO {
    private List<UserProjection> userList;
    private List<GroupDTO> groupList;
    private List<RoleDto> roleList;
}
