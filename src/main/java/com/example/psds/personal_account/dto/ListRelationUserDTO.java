package com.example.psds.personal_account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListRelationUserDTO {
    private List<RelationUsersDTO> listRelation;
    private List<UserProjection> mentorList;
}
