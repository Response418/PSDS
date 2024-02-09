package com.example.psds.personal_account.service;

import com.example.psds.personal_account.dto.GroupDTO;
import com.example.psds.personal_account.dto.UserDTO;
import com.example.psds.personal_account.mapper.ModelWithGroupToObjectWithGroup;
import com.example.psds.personal_account.model.Group;
import com.example.psds.personal_account.model.Role;
import com.example.psds.personal_account.model.RoleInGroup;
import com.example.psds.personal_account.model.User;
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
@Transactional
@AllArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final RoleInGroupRepository roleInGroupRepository;
    private final UserRepository userRepository;
    private final ModelWithGroupToObjectWithGroup modelWithGroupToObjectWithGroup;

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

    public void changeRoleInGroupByUserId(Long groupId, Long userId, Role role){
        RoleInGroup roleInGroup = roleInGroupRepository.findByGroup_IdAndUser_Id(groupId, userId);
        if (roleInGroup!=null) {
            roleInGroup.setRoles(Collections.singleton(role));
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
            roleInGroup.setRoles(Collections.singleton(role));
        }
        roleInGroupRepository.save(roleInGroup);
    }
}