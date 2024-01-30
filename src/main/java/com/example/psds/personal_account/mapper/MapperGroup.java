package com.example.psds.personal_account.mapper;

import com.example.psds.personal_account.dto.GroupDTO;
import com.example.psds.personal_account.model.Group;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;
@Component
@Mapper(componentModel = "spring")
public interface MapperGroup {
    Group objectToModel(GroupDTO groupDTO);
    GroupDTO modelToObject(Group group);
}
