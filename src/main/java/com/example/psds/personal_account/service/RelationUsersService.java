package com.example.psds.personal_account.service;

import com.example.psds.personal_account.model.RelationUsers;
import com.example.psds.personal_account.repository.RelationUsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RelationUsersService {
    private final RelationUsersRepository relationUsersRepository;

    public RelationUsersService(final RelationUsersRepository relationUsersRepository) {
        this.relationUsersRepository = relationUsersRepository;
    }

    @Transactional
    public void deleteRelationUsersByGroupId(Long groupId){
        RelationUsers relationUsers = relationUsersRepository.findRelationUsersByGroup_Id(groupId);
        if (relationUsers!=null) {
            relationUsersRepository.delete(relationUsers);
        }
    }

    @Transactional
    public void deleteRelationUsersByUserIdOrMasterId(Long userId, Long masterId){
        RelationUsers relationUsers = relationUsersRepository.findRelationUsersByStudent_IdOrMaster_Id(userId, masterId);
        if(relationUsers!=null) {
            relationUsersRepository.delete(relationUsers);
        }
    }
}
