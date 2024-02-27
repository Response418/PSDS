package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.Group;
import com.example.psds.personal_account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findGroupById(Long id);

    @Query("SELECT g FROM Group g WHERE g.id <> :groupId")
    List<Group> findAllExceptGroupId(Long groupId);
}


