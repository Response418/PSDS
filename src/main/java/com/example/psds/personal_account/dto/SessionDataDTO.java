package com.example.psds.personal_account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SessionDataDTO {
    private Long userId;
    private Long groupId;
}
