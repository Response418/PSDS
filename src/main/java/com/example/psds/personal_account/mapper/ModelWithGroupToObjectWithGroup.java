package com.example.psds.personal_account.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ModelWithGroupToObjectWithGroup {
    com.example.psds.personal_account.model.Group objectToModel(com.example.psds.personal_account.dto.Group group);
    com.example.psds.personal_account.dto.Group modelToObject(com.example.psds.personal_account.model.Group group);
}
