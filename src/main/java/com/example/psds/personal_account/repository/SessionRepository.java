package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findByUserId(Long userId);
    Session findBySessionId(String sessionId);

    long deleteBySessionId(String SessionId);
}
