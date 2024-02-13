package com.example.psds.personal_account.service;

import com.example.psds.personal_account.dto.UserDTO;
import com.example.psds.personal_account.dto.UserProjection;
import com.example.psds.personal_account.mapper.ModelWithUserToObjectWithUser;
import com.example.psds.personal_account.model.RelationUsers;
import com.example.psds.personal_account.model.Session;
import com.example.psds.personal_account.model.User;
import com.example.psds.personal_account.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class RelationUsersService {
    private final RelationUsersRepository relationUsersRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ModelWithUserToObjectWithUser modelWithUserToObjectWithUser;
    private final SessionRepository sessionRepository;
    private final RoleInGroupRepository roleInGroupRepository;

    public void createRelationUsersByGroupIdAndUserId(Long groupId, Long userId, UserDTO mentor){
        RelationUsers relationUsers = relationUsersRepository.findRelationUsersByGroup_IdAndStudent_Id(groupId, userId);
        if (relationUsers==null){
            relationUsers = new RelationUsers();
            relationUsers.setGroup(groupRepository.findGroupById(groupId));
            relationUsers.setStudent(userRepository.findUserById(userId));
            relationUsers.setMaster(modelWithUserToObjectWithUser.objectToModel(mentor));
        }else if (!Objects.equals(relationUsers.getMaster().getId(), mentor.getId()) ){
            User mentorModel = modelWithUserToObjectWithUser.objectToModel(mentor);
            relationUsers.setMaster(mentorModel);
        }
        relationUsersRepository.save(relationUsers);
    }

    public void deleteRelationUsersByGroupId(Long groupId){
        RelationUsers relationUsers = relationUsersRepository.findRelationUsersByGroup_Id(groupId);
        if (relationUsers!=null) {
            relationUsersRepository.delete(relationUsers);
        }
    }

    public void deleteRelationUsersByUserIdOrMasterId(Long userId, Long masterId){
        RelationUsers relationUsers = relationUsersRepository.findRelationUsersByStudent_IdOrMaster_Id(userId, masterId);
        if(relationUsers!=null) {
            relationUsersRepository.delete(relationUsers);
        }
    }

    public List<UserProjection> findListUserForGroup(HttpServletRequest request) {
        Session session = sessionRepository.findBySessionId(request.getSession().getId());
        List<Long> userId = roleInGroupRepository.findUserIdByGroupId(session.getGroup().getId());
        List<UserProjection> user = new ArrayList<>();
        for (Long id : userId) {
            user.add(userRepository.findUserByUserId(id));
        }
        return user;
    }
}
