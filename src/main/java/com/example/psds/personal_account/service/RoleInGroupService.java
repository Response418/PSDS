package com.example.psds.personal_account.service;

import com.example.psds.personal_account.dto.GroupDTO;
import com.example.psds.personal_account.dto.ListRoleInGroupDTO;
import com.example.psds.personal_account.dto.RoleDto;
import com.example.psds.personal_account.dto.moderator.RoleInGroupDto;
import com.example.psds.personal_account.mapper.ModelWithGroupToObjectWithGroup;
import com.example.psds.personal_account.model.*;
import com.example.psds.personal_account.repository.GroupRepository;
import com.example.psds.personal_account.repository.RoleInGroupRepository;
import com.example.psds.personal_account.repository.RoleRepository;
import com.example.psds.personal_account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RoleInGroupService {
    private final RoleInGroupRepository roleInGroupRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;
    private final ModelWithGroupToObjectWithGroup modelWithGroupToObjectWithGroup;


    public void create(RoleInGroupDto roleInGroupDto) {
        Group group = groupRepository.findById(roleInGroupDto.getGroupId()).orElseThrow();
        User user = userRepository.findById(roleInGroupDto.getUserId()).orElseThrow();
        Role role = roleRepository.findById(roleInGroupDto.getRoleId()).orElseThrow();
        RoleInGroup roleInGroup = new RoleInGroup();
        roleInGroup.setGroup(group);
        roleInGroup.setUser(user);
        roleInGroup.setRole(role);
        log.info("Saving new role in group");
        roleInGroupRepository.save(roleInGroup);
    }

    public ListRoleInGroupDTO getListsForRoleInGroup() {
        List<Group> group = groupRepository.findAll();
        List<GroupDTO> groupDTO = new ArrayList<>();
        for (Group group1 : group) {
            groupDTO.add(modelWithGroupToObjectWithGroup.modelToObject(group1));
        }
        List<Role> roles = roleRepository.findAll();
        ListRoleInGroupDTO list = new ListRoleInGroupDTO();
        list.setUserList(userRepository.findListUserForRoleInGroup());
        list.setGroupList(groupDTO);
        list.setRoleList(returnRoleDto(roles));
        return list;
    }


    private List<RoleDto> returnRoleDto(List<Role> roles){
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role role : roles) {
            RoleDto roleDto = new RoleDto();
            switch (String.valueOf(role.getName())) {
                case "ROLE_STUDENT":
                    roleDto.setId(role.getId());
                    roleDto.setName("Студент");
                    roleDtos.add(roleDto);
                    break;
                case "ROLE_MENTOR":
                    roleDto.setId(role.getId());
                    roleDto.setName("Наставник");
                    roleDtos.add(roleDto);
                    break;
                case "ROLE_DIRECTOR":
                    roleDto.setId(role.getId());
                    roleDto.setName("Руководитель группы");
                    roleDtos.add(roleDto);
                    break;
            }
        }
        return roleDtos;
    }
}
