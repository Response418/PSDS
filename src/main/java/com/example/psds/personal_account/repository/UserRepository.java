package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.Role;
import com.example.psds.personal_account.dto.UserProjection;
import com.example.psds.personal_account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);

    User findUserById(Long id);

    @Query("SELECT u.id AS id, u.lastName AS lastName, u.firstName AS firstName, " +
            "u.fatherName AS fatherName FROM User u")
    List<UserProjection> findListUserForRoleInGroup();
}
