package com.example.psds.personal_account.response;

import com.example.psds.personal_account.dto.RelationUsersDTO;
import com.example.psds.personal_account.exception.ServiceException;
import com.example.psds.personal_account.mapper.MapperRelationUsers;
import com.example.psds.personal_account.model.RelationUsers;
import com.example.psds.personal_account.model.Role;
import com.example.psds.personal_account.service.GroupService;
import com.example.psds.personal_account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.psds.personal_account.exception.CustomErrorMessage.*;

@Service
@RequiredArgsConstructor
public class GroupResponseBuilder {
    private final GroupService groupService;
    private final UserService userService;
    private final MapperRelationUsers mapperRelationUsers;

    public ResponseEntity<?> getStudentsByMaster(Long userId, Long groupId) throws ServiceException {

        if(!userService.hasUserById(userId))
            throw new ServiceException(USER_NOT_FOUND);

        if(!groupService.hasGroupById(groupId))
            throw new ServiceException(GROUP_NOT_FOUND);

        if(!userService.IfUserBelongGroup(userId, groupId))
            throw new ServiceException(USER_NOT_BELONG_TO_GROUP);

        if(!userService.ifRoleAccessByUserId(userId, Role.ROLE_MENTOR))
            throw new ServiceException(ACCESS_DENIED);


        List<RelationUsers> relationUsers = groupService.getStudentsByMaster(userId, groupId);

        List<RelationUsersDTO> relationUsersDTO = relationUsers
                .stream()
                .map(mapperRelationUsers::modelToObject)
                .collect(Collectors.toList());

        return new ResponseEntity<>(relationUsersDTO, HttpStatus.OK);
    }
}
