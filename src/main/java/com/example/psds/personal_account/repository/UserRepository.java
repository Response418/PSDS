package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findUserById(Long userId);
    List<User> findAllByRoleInGroups_GroupId(Long groupId);
}