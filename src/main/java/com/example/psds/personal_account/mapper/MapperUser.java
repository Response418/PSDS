package com.example.psds.personal_account.mapper;
import com.example.psds.personal_account.dto.UserDTO;
import com.example.psds.personal_account.model.User;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;
@Component
@Mapper(componentModel = "spring")
public interface MapperUser {
    User objectToModel(UserDTO userDTO);
    UserDTO modelToObject(User user);
}
