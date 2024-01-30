package com.example.psds.personal_account.service;

import com.example.psds.personal_account.model.Group;
import com.example.psds.personal_account.model.RelationUsers;
import com.example.psds.personal_account.model.User;
import com.example.psds.personal_account.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final RelationUsersRepository relationUsersRepository;
    private final GroupRepository groupRepository;

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
