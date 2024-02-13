package com.example.psds.personal_account.service;

import com.example.psds.personal_account.model.Group;
import com.example.psds.personal_account.model.RoleInGroup;
import com.example.psds.personal_account.repository.GroupRepository;
import com.example.psds.personal_account.repository.RoleInGroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.psds.personal_account.dto.GroupDTO;
import com.example.psds.personal_account.mapper.ModelWithGroupToObjectWithGroup;
import com.example.psds.personal_account.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class GroupService {
    private final GroupRepository groupRepository;
    private final RoleInGroupRepository roleInGroupRepository;
    private final UserService userService;

    private final ModelWithGroupToObjectWithGroup modelWithGroupToObjectWithGroup;

    public void createGroup(GroupDTO groupDTO){
        Group groupModel = modelWithGroupToObjectWithGroup.objectToModel(groupDTO);
        log.info("Saving new group with name: {}", groupDTO.getName());
        groupRepository.save(groupModel);
    }

    public List<GroupDTO> findByUserId(Long userId) {
        List<RoleInGroup> roleInGroups = roleInGroupRepository.findByUserId(userId);
        List<Group> groups = new ArrayList<>();
        for (RoleInGroup roleInGroup : roleInGroups) {
            groups.add(groupRepository.findById(roleInGroup.getGroup().getId()).orElseThrow());
        }
        List<GroupDTO> groupsByUserIdDtoList = new ArrayList<>();
        for (Group group : groups) {
            groupsByUserIdDtoList.add(modelWithGroupToObjectWithGroup.modelToObject(group));
        }
        return groupsByUserIdDtoList;
    }

    public GroupDTO selectGroup(Long groupId, Long userId) {
        RoleInGroup roleInGroup = roleInGroupRepository.findByGroupIdAndUserId(groupId, userId);
        userService.editRoleUser(userId, String.valueOf(roleInGroup.getRole().getName()));
        log.info("The user's role has been changed");
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
}