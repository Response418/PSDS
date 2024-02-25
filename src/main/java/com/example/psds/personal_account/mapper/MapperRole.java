package com.example.psds.personal_account.mapper;
import com.example.psds.personal_account.dto.RoleDto;
import com.example.psds.personal_account.model.Role;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MapperRole {
    RoleDto modelToObject (Role role);
    Role objectToModel (RoleDto roleDto);
}
