package com.example.psds.personal_account.mapper;

import com.example.psds.personal_account.dto.GroupDTO;
import com.example.psds.personal_account.model.Group;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ModelWithGroupToObjectWithGroup {
    Group objectToModel(GroupDTO group);
    GroupDTO modelToObject(Group group);

    GroupDTO modelToObject(Group group, List<String> userRoles);
}
