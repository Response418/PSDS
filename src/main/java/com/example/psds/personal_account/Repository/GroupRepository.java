package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.Group;
import com.example.psds.personal_account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    public Group findGroupById(Long groupId);
}