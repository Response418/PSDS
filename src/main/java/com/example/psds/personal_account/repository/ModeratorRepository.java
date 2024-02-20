package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeratorRepository extends JpaRepository<Moderator, Long> {
}
