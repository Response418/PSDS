package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.RelationUsers;
import com.example.psds.personal_account.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RelationUsersRepository extends JpaRepository<RelationUsers, Long> {
    RelationUsers findRelationUsersByGroup_Id(Long groupId);
    List<RelationUsers> findRelationUsersByGroupId(Long groupId);

    List<RelationUsers> findRelationUsersByGroupIdAndMasterId(Long groupId, Long masterId);
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
    RelationUsers findByStudentIdAndGroupId(Long userId, Long groupId);

    @Query("""
        select ru.student.id
            from RelationUsers ru
                where ru.id = :id
    """)
    Long getStudentIdById(Long id);

    List<RelationUsers> findAllByStudent(User student);

    @Modifying
    @Transactional
    @Query("DELETE FROM RelationUsers r WHERE r.student.id = :studentId AND r.group.id = :groupId")
    void deleteByStudentIdAndGroupId(@Param("studentId") Long studentId, @Param("groupId") Long groupId);
}

