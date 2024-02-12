package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.RoleInGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleInGroupRepository extends JpaRepository<RoleInGroup, Long> {
    List<RoleInGroup> findByUserId (Long id);

    RoleInGroup findByGroupIdAndUserId(Long groupId, Long userId);


public interface RoleInGroupRepository extends JpaRepository<RoleInGroup, Long> {
    RoleInGroup findByGroup_IdAndUser_Id(Long groupId, Long userId);
}
