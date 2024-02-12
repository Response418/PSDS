package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findByUserId(Long userId);
    Session findBySessionId(String sessionId);
}
