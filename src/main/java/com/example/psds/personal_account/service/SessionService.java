package com.example.psds.personal_account.service;

import com.example.psds.personal_account.model.Role;
import com.example.psds.personal_account.model.RoleInGroup;
import com.example.psds.personal_account.model.Session;
import com.example.psds.personal_account.model.User;
import com.example.psds.personal_account.repository.GroupRepository;
import com.example.psds.personal_account.repository.SessionRepository;
import com.example.psds.personal_account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SessionService {
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public void createSession(Long userId, String sessionId) {
        Session session = sessionRepository.findByUserId(userId);
        if(session == null){
            session = new Session();
            session.setUser(userRepository.findById(userId).orElseThrow());
            session.setSessionId(sessionId);
            session.setDateOfCreated(LocalDateTime.now());
            log.info("Saving new session with id {},", sessionId);
            sessionRepository.save(session);
        } else {
            editSession(session, sessionId);
        }
    }

    public void editSession(Session session, String sessionId) {
        if(!session.getSessionId().equals(sessionId)){
            session.setSessionId(sessionId);
            session.setDateOfCreated(LocalDateTime.now());
            log.info("Changing session data");
            sessionRepository.save(session);
        }
    }

    public void editGroupInSession(User user, Long groupId) {
        Session session = sessionRepository.findByUserId(user.getId());
        session.setGroup(groupRepository.findById(groupId).orElseThrow());
        log.info("Changing a group in a session");
        sessionRepository.save(session);
    }

    public List<Role> getListRole(Session session) {
        User user = session.getUser();
        return user.getRoleInGroups()
                .stream()
                .map(RoleInGroup::getRole)
                .toList();
    }

    public void deleteSession(String sessionId) {
        Session session = sessionRepository.findBySessionId(sessionId);
        sessionRepository.deleteById(session.getId());
    }
}
