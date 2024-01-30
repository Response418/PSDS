package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.RelationUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationUsersRepository extends JpaRepository<RelationUsers, Long> {
    RelationUsers findRelationUsersByGroup_Id(Long groupId);

    RelationUsers findRelationUsersByStudent_IdOrMaster_Id(Long userId, Long masterId);
}
