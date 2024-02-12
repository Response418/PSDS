package com.example.psds.personal_account.service;

import com.example.psds.personal_account.dto.authentication.GroupsForUserDto;
import com.example.psds.personal_account.mapper.ModelGroupsForUserToObjectGroupsForUser;
import com.example.psds.personal_account.model.*;
import com.example.psds.personal_account.repository.GroupRepository;
import com.example.psds.personal_account.repository.RelationUsersRepository;
import com.example.psds.personal_account.repository.RoleInGroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.psds.personal_account.dto.GroupDTO;
import com.example.psds.personal_account.dto.UserDTO;
import com.example.psds.personal_account.mapper.ModelWithGroupToObjectWithGroup;
import com.example.psds.personal_account.model.Group;
import com.example.psds.personal_account.model.RoleInGroup;
import com.example.psds.personal_account.repository.GroupRepository;
import com.example.psds.personal_account.repository.RoleInGroupRepository;
import com.example.psds.personal_account.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class GroupService {
    private final GroupRepository groupRepository;
    private final RoleInGroupRepository roleInGroupRepository;
    private final ModelGroupsForUserToObjectGroupsForUser modelGroupsForUserToObjectGroupsForUser;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ModelWithGroupToObjectWithGroup modelWithGroupToObjectWithGroup;
    private final RelationUsersRepository relationUsersRepository;
    private final GroupRepository groupRepository;


    public void createGroup(GroupsForUserDto groupDto) {
        Group group = new Group();
        group.setName(groupDto.getName());
        group.setDescription(groupDto.getDescription());
        log.info("Saving new group with name: {}", groupDto.getName());
        groupRepository.save(group);
    }

    public List<GroupsForUserDto> findByUserId(Long userId) {
        List<RoleInGroup> roleInGroups = roleInGroupRepository.findByUserId(userId);
        List<Group> groups = new ArrayList<>();
        for (RoleInGroup roleInGroup : roleInGroups) {
            groups.add(groupRepository.findById(roleInGroup.getGroup().getId()).orElseThrow());
        }
        List<GroupsForUserDto> groupsByUserIdDtoList = new ArrayList<>();
        for (Group group : groups) {
            groupsByUserIdDtoList.add(modelGroupsForUserToObjectGroupsForUser.modelToObject(group));
        }
        return groupsByUserIdDtoList;
    }

    public GroupsForUserDto selectGroup(Long groupId, Long userId) {
        RoleInGroup roleInGroup = roleInGroupRepository.findByGroupIdAndUserId(groupId, userId);
        userService.editRoleUser(userId, String.valueOf(roleInGroup.getRole().getName()));
        log.info("The user's role has been changed");
        Group group = groupRepository.findById(groupId).orElseThrow();
        return modelGroupsForUserToObjectGroupsForUser.modelToObject(group);
    }

    public List<GroupDTO> getGroupList(){
        List<com.example.psds.personal_account.model.Group> groupModelList = groupRepository.findAll();
        List<GroupDTO> groupObjectList = new ArrayList<>();
        for(int i=0; i<groupModelList.size(); i++){
            groupObjectList.add(modelWithGroupToObjectWithGroup.modelToObject(groupModelList.get(i)));
        }
        return groupObjectList;
    }

    public void createGroup(GroupDTO groupDTO){
        com.example.psds.personal_account.model.Group groupModel = modelWithGroupToObjectWithGroup.objectToModel(groupDTO);
        groupRepository.save(groupModel);
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

    public boolean hasGroupById(Long groupId){
        Group group = getGroupById(groupId);
        return (group != null);
    }

    public Group getGroupById(Long groupId) {
        return groupRepository.findGroupById(groupId);
    }

    public List<RelationUsers> getStudentsByMaster(Long masterId, Long groupId) {
        return relationUsersRepository.findAllByMasterIdAndGroupId(masterId, groupId);
    }
}