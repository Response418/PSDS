package com.example.psds.personal_account.service;

import com.example.psds.personal_account.dto.moderator.RoleInGroupDto;
import com.example.psds.personal_account.model.*;
import com.example.psds.personal_account.repository.GroupRepository;
import com.example.psds.personal_account.repository.RoleInGroupRepository;
import com.example.psds.personal_account.repository.RoleRepository;
import com.example.psds.personal_account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RoleInGroupService {
    private final RoleInGroupRepository roleInGroupRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;


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
}
