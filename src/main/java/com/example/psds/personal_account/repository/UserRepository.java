package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findUserById(Long id);

    List<User> findAllByRoleInGroups_GroupId(Long groupId);
}
