package com.example.psds.personal_account.service;

import com.example.psds.personal_account.dto.authentication.GroupsForUserDto;
import com.example.psds.personal_account.model.*;
import com.example.psds.personal_account.model.Group;
import com.example.psds.personal_account.model.RoleInGroup;
import com.example.psds.personal_account.repository.GroupRepository;
import com.example.psds.personal_account.repository.RelationUsersRepository;
import com.example.psds.personal_account.repository.RoleInGroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.psds.personal_account.dto.GroupDTO;
import com.example.psds.personal_account.mapper.ModelWithGroupToObjectWithGroup;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static com.example.psds.personal_account.model.ERole.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class GroupService {
    private final GroupRepository groupRepository;
    private final RoleInGroupRepository roleInGroupRepository;
    private final UserService userService;

    private final ModelWithGroupToObjectWithGroup modelWithGroupToObjectWithGroup;
    private final RelationUsersRepository relationUsersRepository;

    public void save(Group group){
        groupRepository.save(group);
    }

    public void createGroup(GroupDTO groupDTO){
        Group groupModel = modelWithGroupToObjectWithGroup.objectToModel(groupDTO);
        log.info("Saving new group with name: {}", groupDTO.getName());
        groupRepository.save(groupModel);
    }

    public List<GroupDTO> findByUserId(Long userId) {
        List<RoleInGroup> roleInGroups = roleInGroupRepository.findByUserId(userId);
        List<GroupDTO> groupsDto = new ArrayList<>();
        for (RoleInGroup roleInGroup : roleInGroups) {
            Group group = groupRepository.findById(roleInGroup.getGroup().getId()).orElseThrow();
            boolean groupExists = groupsDto.stream().anyMatch(g -> g.getId() == group.getId());
            if (!groupExists) {
                List<RoleInGroup> r = roleInGroupRepository.findByGroupIdAndUserId(group.getId(), userId);
                List<String> userRole = new ArrayList<>();
                for (RoleInGroup inGroup : r) {
                    userRole.add(inGroup.getRole().getName().toString());
                }
                groupsDto.add(new GroupDTO(group.getId(), group.getName(), group.getDescription(), userRole));
            }
        }
        return groupsDto;
    }


    public GroupDTO selectGroup(Long groupId, Long userId) {
        List<RoleInGroup> roleInGroups = roleInGroupRepository.findByGroupIdAndUserId(groupId, userId);
        boolean hasDirectorRole = roleInGroups.stream()
                .map(RoleInGroup::getRole)
                .anyMatch(role -> role.getName().equals(ERole.ROLE_DIRECTOR));
        boolean hasMentorRole = roleInGroups.stream()
                .map(RoleInGroup::getRole)
                .anyMatch(role -> role.getName().equals(ERole.ROLE_MENTOR));
        boolean hasStudentRole = roleInGroups.stream()
                .map(RoleInGroup::getRole)
                .anyMatch(role -> role.getName().equals(ERole.ROLE_STUDENT));
        if (hasDirectorRole) {
            userService.editRoleUser(userId, ERole.ROLE_DIRECTOR.toString());
        } else if (hasMentorRole) {
            userService.editRoleUser(userId, ROLE_MENTOR.toString());
        } else if (hasStudentRole) {
            userService.editRoleUser(userId, ROLE_STUDENT.toString());
        }
        Group group = groupRepository.findById(groupId).orElseThrow();
        return modelWithGroupToObjectWithGroup.modelToObject(group);
    }

    public List<GroupDTO> getGroupList(){
        List<com.example.psds.personal_account.model.Group> groupModelList = groupRepository.findAll();
        List<GroupDTO> groupObjectList = new ArrayList<>();
        for(int i=0; i<groupModelList.size(); i++){
            groupObjectList.add(modelWithGroupToObjectWithGroup.modelToObject(groupModelList.get(i)));
        }
        return groupObjectList;
    }



    public GroupDTO getGroupById(Long groupId){
        GroupDTO groupObject = modelWithGroupToObjectWithGroup.modelToObject(groupRepository.findGroupById(groupId));
        return groupObject;
    }

    public void updateGroup(GroupDTO group){
        com.example.psds.personal_account.model.Group groupModel = modelWithGroupToObjectWithGroup.objectToModel(group);
        groupRepository.save(groupModel);
    }

    public void deleteGroup(Long groupId){
        log.info("Deleting a group");
        groupRepository.deleteById(groupId);
    }

    /*public void changeRoleInGroupByUserId(Long groupId, Long userId, Role role){
        RoleInGroup roleInGroup = roleInGroupRepository.findByGroupIdAndUserId(groupId, userId);
        if (roleInGroup!=null) {
            roleInGroup.setRole(Collections.singleton(role));
        }else{
            roleInGroup = new RoleInGroup();
            Group group = groupRepository.findGroupById(groupId);
            if (group==null){
                return;
            }
            roleInGroup.setGroup(group);
            User user = userRepository.findUserById(userId);
            if (user==null){
                return;
            }
            roleInGroup.setUser(user);
            roleInGroup.setRole(Collections.singleton(role));
        }
        roleInGroupRepository.save(roleInGroup);
    }*/

    public List<RelationUsers> getStudentsByMaster(Long masterId, Long groupId) {
        return relationUsersRepository.findAllByMasterIdAndGroupId(masterId, groupId);
    }
}