package com.example.psds.personal_account.response;

import com.example.psds.personal_account.dto.RelationUsersDTO;
import com.example.psds.personal_account.mapper.MapperRelationUsers;
import com.example.psds.personal_account.model.RelationUsers;
import com.example.psds.personal_account.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupResponseBuilder {
    private final GroupService groupService;
    private final MapperRelationUsers mapperRelationUsers;

    public ResponseEntity<?> getStudentsByMaster(Long userId, Long groupId){

        List<RelationUsers> relationUsers = groupService.getStudentsByMaster(userId, groupId);


        List<RelationUsersDTO> relationUsersDTO = relationUsers
                .stream()
                .map(mapperRelationUsers::modelToObject)
                .collect(Collectors.toList());

        return new ResponseEntity<>(relationUsersDTO, HttpStatus.OK);
    }
}
