package com.example.psds.personal_account.dto.moderator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleInGroupDto {
    private Long userId;
    private Long roleId;
    private Long groupId;
}
