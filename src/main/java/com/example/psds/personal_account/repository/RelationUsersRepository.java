package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.RelationUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelationUsersRepository extends JpaRepository<RelationUsers, Long> {
    RelationUsers findRelationUsersByGroup_Id(Long groupId);
    List<RelationUsers> findRelationUsersByGroupId(Long groupId);
    RelationUsers findRelationUsersByStudent_IdOrMaster_Id(Long userId, Long masterId);
    RelationUsers findRelationUsersByGroup_IdAndStudent_Id(Long groupId, Long userId);

    RelationUsers findByStudentId(Long userId);
}
