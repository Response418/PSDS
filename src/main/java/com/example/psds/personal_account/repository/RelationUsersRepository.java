package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.RelationUsers;
import com.example.psds.personal_account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.List;

public interface RelationUsersRepository extends JpaRepository<RelationUsers, Long> {
    RelationUsers findRelationUsersByGroup_Id(Long groupId);
    List<RelationUsers> findRelationUsersByGroupId(Long groupId);
    RelationUsers findRelationUsersByStudent_IdOrMaster_Id(Long userId, Long masterId);
    RelationUsers findRelationUsersByGroup_IdAndStudent_Id(Long groupId, Long userId);
    @Query("""
        select ru
            from RelationUsers ru
                where ru.group.id = :groupId
                and ru.master.id = :masterId
    """)
    List<RelationUsers> findAllByMasterIdAndGroupId(Long masterId, Long groupId);

    RelationUsers findByStudentId(Long userId);

    @Query("""
        select ru.student.id
            from RelationUsers ru
                where ru.id = :id
    """)
    Long getStudentIdById(Long id);

    List<RelationUsers> findAllByStudent(User student);
}
