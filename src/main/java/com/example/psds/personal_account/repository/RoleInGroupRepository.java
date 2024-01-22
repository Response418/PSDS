package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleInGroupRepository extends JpaRepository<User, Long> {

}
