package com.example.psds.personal_account.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ModelWithUserToObjectWithUser {
    com.example.psds.personal_account.model.User objectToModel (com.example.psds.personal_account.dto.User user);
    com.example.psds.personal_account.dto.User modelToObject (com.example.psds.personal_account.model.User user);
}
