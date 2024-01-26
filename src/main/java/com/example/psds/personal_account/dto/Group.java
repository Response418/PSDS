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
public class Group {
    private Long id;
    private String name;
    private String description;
    private List<RoleInGroup> roleInGroups = new ArrayList<>();
}
