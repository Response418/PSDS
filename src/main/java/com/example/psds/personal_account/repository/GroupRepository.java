package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findGroupById(Long id);
}


