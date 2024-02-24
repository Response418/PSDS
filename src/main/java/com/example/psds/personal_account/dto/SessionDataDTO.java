package com.example.psds.personal_account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SessionDataDTO {
    private Long userId;
    private Long groupId;
    private List<String> roles;
}
