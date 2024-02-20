package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.ERole;
import com.example.psds.personal_account.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole name);
}
