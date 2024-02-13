package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.RoleInGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleInGroupRepository extends JpaRepository<RoleInGroup, Long> {
    List<RoleInGroup> findByUserId (Long id);
    RoleInGroup findByGroupIdAndUserId(Long groupId, Long userId);

    @Query(""" 
            select user.id from RoleInGroup where group.id = :groupId
            """)
    List<Long> findUserIdByGroupId(@Param("groupId") Long groupId);
}
