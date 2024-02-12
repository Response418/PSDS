package com.example.psds.personal_account.service;

import com.example.psds.personal_account.dto.authentication.GroupsForUserDto;
import com.example.psds.personal_account.mapper.ModelGroupsForUserToObjectGroupsForUser;
import com.example.psds.personal_account.model.Group;
import com.example.psds.personal_account.model.RoleInGroup;
import com.example.psds.personal_account.repository.GroupRepository;
import com.example.psds.personal_account.repository.RoleInGroupRepository;
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
public class GroupService {
    private final GroupRepository groupRepository;
    private final RoleInGroupRepository roleInGroupRepository;
    private final ModelGroupsForUserToObjectGroupsForUser modelGroupsForUserToObjectGroupsForUser;
    private final UserService userService;
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
}
