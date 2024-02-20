package com.example.psds.personal_account.service;

import com.example.psds.personal_account.dto.ListRelationUserDTO;
import com.example.psds.personal_account.dto.RelationUsersDTO;
import com.example.psds.personal_account.dto.UserDTO;
import com.example.psds.personal_account.dto.UserProjection;
import com.example.psds.personal_account.mapper.ModelRelationUsersToObjectRelationUsers;
import com.example.psds.personal_account.mapper.ModelWithUserToObjectWithUser;
import com.example.psds.personal_account.model.*;
import com.example.psds.personal_account.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.*;

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
    private final RoleRepository roleRepository;
    private final ModelRelationUsersToObjectRelationUsers modelRelationUsersToObjectRelationUsers;

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

    public RelationUsers getRelationUserByUserId(Long userId){
        return relationUsersRepository.findByStudentId(userId);
    }

    public void createRelationUser(RelationUsers relationUsers){
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


    public ListRelationUserDTO findListMentorForGroup(Principal principal) {
        Role roleStudent = roleRepository.findByName(ERole.ROLE_STUDENT);
        Optional<User> user = userRepository.findByEmail(principal.getName());

        Session session = sessionRepository.findByUserId(user.get().getId());
        Long groupId = session.getGroup().getId();


        List<UserProjection> userStudent = new ArrayList<>(roleInGroupRepository.
                findUsersByRoleIdAndGroupId(roleStudent, groupId));



        Set<UserProjection> userMentor= new HashSet<>(roleInGroupRepository.findUsersByGroupId(groupId));
        List<RelationUsers> relationUsers = relationUsersRepository.findRelationUsersByGroupId(groupId);
        List<RelationUsersDTO> relationUsersDTOs = new ArrayList<>();
        for (RelationUsers relationUser : relationUsers) {
            relationUsersDTOs.add(modelRelationUsersToObjectRelationUsers.modelToObject(relationUser));
        }
        ListRelationUserDTO listRelationUserDTO = new ListRelationUserDTO();
        listRelationUserDTO.setListRelation(relationUsersDTOs);
        listRelationUserDTO.setGroupId(groupId);
        listRelationUserDTO.setStudentList(userStudent);
        listRelationUserDTO.setMentorList(userMentor);
        return listRelationUserDTO;
    }
}
