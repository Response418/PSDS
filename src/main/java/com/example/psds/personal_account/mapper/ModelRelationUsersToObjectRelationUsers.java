package com.example.psds.personal_account.mapper;

import com.example.psds.personal_account.dto.RelationUsersDTO;
import com.example.psds.personal_account.model.RelationUsers;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ModelRelationUsersToObjectRelationUsers {
    RelationUsers objectToModel (RelationUsersDTO relationUsersDTO);
    RelationUsersDTO modelToObject (RelationUsers relationUsers);
}
