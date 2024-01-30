package com.example.psds.personal_account.dto;

import com.example.psds.personal_account.model.Group;
import com.example.psds.personal_account.model.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RelationUsersDTO {
    private Long id;
    private Group group;
    private User student;
    private User master;
}
