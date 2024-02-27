package com.example.psds.personal_account.service;

import com.example.psds.personal_account.dto.ListRelationUserDTO;
import com.example.psds.personal_account.dto.RelationUsersDTO;
import com.example.psds.personal_account.dto.UserDTO;
import com.example.psds.personal_account.dto.UserProjection;
import com.example.psds.personal_account.mapper.ModelRelationUsersToObjectRelationUsers;
import com.example.psds.personal_account.mapper.ModelWithUserToObjectWithUser;
import com.example.psds.personal_account.model.*;
import com.example.psds.personal_account.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.security.Principal;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class RelationUsersService {
    private final RelationUsersRepository relationUsersRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ModelWithUserToObjectWithUser modelWithUserToObjectWithUser;
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


    public RelationUsers getRelationUserByUserIdAndGroupId(Long userId, Long groupId){
        return relationUsersRepository.findByStudentIdAndGroupId(userId, groupId);
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


    public ListRelationUserDTO findListMentorForGroup(Principal principal, Long groupId) {
        Role roleStudent = roleRepository.findByName(ERole.ROLE_STUDENT);
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

    public Long getUserIdByLink(Long linkUserId) {
        return relationUsersRepository.getStudentIdById(linkUserId);
    }

    public List<RelationUsersDTO> getListRelationByStudentId(Long studentId) {
        User student = userRepository.findUserById(studentId);
        List<RelationUsers> relationUsers = relationUsersRepository.findAllByStudent(student);

        return relationUsers
                .stream()
                .map(modelRelationUsersToObjectRelationUsers::modelToObject)
                .collect(Collectors.toList());
    }

    public RelationUsersDTO getLinkByStudentAndGroup(Long studentId, Long groupId){
        RelationUsers relationUsers = relationUsersRepository.findRelationUsersByGroup_IdAndStudent_Id(groupId, studentId);
        return modelRelationUsersToObjectRelationUsers.modelToObject(relationUsers);
    }

    public void editMentorForGroup(Long mentorId, Long relationId) {
        RelationUsers relationUsers = relationUsersRepository.findById(relationId).orElseThrow();
        relationUsers.setMaster(userRepository.findUserById(mentorId));
        log.info("Changing a mentor for a student with id {}", mentorId);
        relationUsersRepository.save(relationUsers);

        boolean hasMentorRole = roleInGroupRepository.existsByGroupIdAndUserIdAndRoleId(
                relationUsers.getGroup().getId(),
                mentorId,
                roleRepository.findByName(ERole.ROLE_MENTOR).getId()
        );
        if (!hasMentorRole) {
            RoleInGroup roleInGroup = new RoleInGroup();
            roleInGroup.setGroup(groupRepository.findById(relationUsers.getGroup().getId()).orElseThrow());
            roleInGroup.setUser(userRepository.findById(mentorId).orElseThrow());
            roleInGroup.setRole(roleRepository.findByName(ERole.ROLE_MENTOR));
            log.info("Saving a mentor role for a userId {} in a groupId {}", mentorId, relationUsers.getGroup().getId());
            roleInGroupRepository.save(roleInGroup);
        }
    }

    public void deleteByStudentIdAndGroupId(Long userId, Long groupId){
        relationUsersRepository.deleteByStudentIdAndGroupId(userId, groupId);
    }

}
