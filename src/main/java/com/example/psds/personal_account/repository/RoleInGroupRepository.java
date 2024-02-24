package com.example.psds.personal_account.repository;


import com.example.psds.personal_account.dto.UserProjection;
import com.example.psds.personal_account.model.Role;
import com.example.psds.personal_account.model.RoleInGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleInGroupRepository extends JpaRepository<RoleInGroup, Long> {
    List<RoleInGroup> findByUserId (Long id);

    List<RoleInGroup> findByGroupId (Long id);
    List<RoleInGroup> findByGroupIdAndUserId(Long groupId, Long userId);

    boolean existsByGroupIdAndUserIdAndRoleId(Long groupId, Long userId, Long roleId);


    @Query("SELECT r.user.id AS id, r.user.lastName AS lastName, r.user.firstName AS firstName, " +
            "r.user.fatherName AS fatherName FROM RoleInGroup r JOIN r.user u " +
            "WHERE r.role = :role AND r.group.id = :groupId")
    List<UserProjection> findUsersByRoleIdAndGroupId(@Param("role") Role role, @Param("groupId") Long groupId);

    @Query("SELECT DISTINCT r.user.id AS id, r.user.lastName AS lastName, r.user.firstName AS firstName, " +
            "r.user.fatherName AS fatherName FROM RoleInGroup r JOIN r.user u " +
            "WHERE r.group.id = :groupId")
    Set<UserProjection> findUsersByGroupId(@Param("groupId") Long groupId);

    @Query("SELECT COUNT(rg) > 0 FROM RoleInGroup rg WHERE rg.user.id = :userId AND rg.role.id = :roleId AND rg.group.id = :groupId")
    boolean existsByUserIdAndRoleIdAndGroupId(Long userId, Long roleId, Long groupId);
}
