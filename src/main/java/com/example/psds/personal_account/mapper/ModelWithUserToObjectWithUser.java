package com.example.psds.personal_account.mapper;

import com.example.psds.personal_account.dto.UserDTO;
import com.example.psds.personal_account.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ModelWithUserToObjectWithUser {
    User objectToModel (UserDTO userDTO);
    UserDTO modelToObject (User user);
}
