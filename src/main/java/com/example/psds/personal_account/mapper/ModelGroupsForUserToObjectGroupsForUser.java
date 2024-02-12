package com.example.psds.personal_account.mapper;

import com.example.psds.personal_account.dto.authentication.GroupsForUserDto;
import com.example.psds.personal_account.model.Group;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;

@Component
@Mapper(componentModel = "spring")
public interface ModelGroupsForUserToObjectGroupsForUser {
    Group objectToModel(GroupsForUserDto groups);
    GroupsForUserDto modelToObject(Group group);
}
