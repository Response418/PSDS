package com.example.psds.personal_account.response;

import com.example.psds.personal_account.dto.RelationUsersDTO;
import com.example.psds.personal_account.dto.SessionDataDTO;
import com.example.psds.personal_account.exception.ServiceException;
import com.example.psds.personal_account.mapper.MapperRelationUsers;
import com.example.psds.personal_account.model.ERole;
import com.example.psds.personal_account.model.RelationUsers;
import com.example.psds.personal_account.model.Role;
import com.example.psds.personal_account.model.Session;
import com.example.psds.personal_account.repository.SessionRepository;
import com.example.psds.personal_account.service.GroupService;
import com.example.psds.personal_account.service.SessionService;
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
    private final SessionRepository sessionRepository;
    private final SessionService sessionService;

    public ResponseEntity<?> getStudentsByMaster(String sessionId) {

        Session session = sessionRepository.findBySessionId(sessionId);
        Long userId = session.getUser().getId();
        Long groupId = session.getGroup().getId();

        List<RelationUsers> relationUsers = groupService.getStudentsByMaster(userId, groupId);

        List<RelationUsersDTO> relationUsersDTO = relationUsers
                .stream()
                .map(mapperRelationUsers::modelToObject)
                .collect(Collectors.toList());

        return new ResponseEntity<>(relationUsersDTO, HttpStatus.OK);
    }

    public ResponseEntity<?> getDataSession(String sessionId) {
        Session session = sessionRepository.findBySessionId(sessionId);
        Long userId = session.getUser().getId();

        Long groupId = 0L;
        if(session.getGroup() != null)
            groupId = session.getGroup().getId();

        List<String> roles = sessionService
                .getListRole(session)
                .stream()
                .map(Role::getName)
                .map(ERole::name)
                .toList();

        SessionDataDTO sessionDataDTO = new SessionDataDTO(userId, groupId, roles);
        return new ResponseEntity<>(sessionDataDTO, HttpStatus.OK);
    }
}
