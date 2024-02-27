package com.example.psds;

import com.example.psds.personal_account.model.*;
import com.example.psds.personal_account.repository.GroupRepository;
import com.example.psds.personal_account.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private SessionService sessionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSession_shouldCreateNewSession() {
        Long userId = 1L;
        String sessionId = "abc123";

        User user = new User();
        user.setId(userId);

        when(sessionRepository.findByUserId(userId)).thenReturn(null);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        sessionService.createSession(userId, sessionId);

        verify(sessionRepository, times(1)).save(any(Session.class));
    }

    @Test
    void createSession_shouldEditExistingSession() {
        Long userId = 1L;
        String sessionId = "abc123";

        User user = new User();
        user.setId(userId);

        Session session = new Session();
        session.setUser(user);
        session.setSessionId("oldSessionId");

        when(sessionRepository.findByUserId(userId)).thenReturn(session);

        sessionService.createSession(userId, sessionId);

        verify(sessionRepository, times(1)).save(any(Session.class));
    }

    @Test
    void editSession_shouldChangeSessionData() {
        Session session = new Session();
        session.setSessionId("oldSessionId");

        String newSessionId = "newSessionId";

        sessionService.editSession(session, newSessionId);

        assertEquals(newSessionId, session.getSessionId());
        verify(sessionRepository, times(1)).save(session);
    }

    @Test
    void editSession_shouldNotChangeSessionData() {
        Session session = new Session();
        session.setSessionId("oldSessionId");

        String sameSessionId = "oldSessionId";

        sessionService.editSession(session, sameSessionId);

        assertEquals(sameSessionId, session.getSessionId());
        verify(sessionRepository, never()).save(session);
    }

    @Test
    void editGroupInSession_shouldChangeGroupInSession() {
        User user = new User();
        user.setId(1L);

        Long groupId = 1L;
        Group group = new Group();
        group.setId(groupId);

        Session session = new Session();
        session.setUser(user);

        when(sessionRepository.findByUserId(user.getId())).thenReturn(session);
        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));

        sessionService.editGroupInSession(user, groupId);

        assertEquals(group, session.getGroup());
        verify(sessionRepository, times(1)).save(session);
    }

    @Test
    void getListRole_shouldReturnListOfRoles() {
        User user = new User();
        user.setId(1L);

        Role role1 = new Role();
        role1.setId(1L);
        Role role2 = new Role();
        role2.setId(2L);

        RoleInGroup roleInGroup1 = new RoleInGroup();
        roleInGroup1.setRole(role1);
        RoleInGroup roleInGroup2 = new RoleInGroup();
        roleInGroup2.setRole(role2);

        List<RoleInGroup> roleInGroups = new ArrayList<>();
        roleInGroups.add(roleInGroup1);
        roleInGroups.add(roleInGroup2);

        user.setRoleInGroups(roleInGroups);

        Session session = new Session();
        session.setUser(user);

        List<Role> expectedRoles = new ArrayList<>();
        expectedRoles.add(role1);
        expectedRoles.add(role2);

        List<Role> actualRoles = sessionService.getListRole(session);

        assertEquals(expectedRoles, actualRoles);
    }

    @Test
    void deleteSession_shouldDeleteSession() {
        String sessionId = "abc123";

        Session session = new Session();
        session.setId(1L);

        when(sessionRepository.findBySessionId(sessionId)).thenReturn(session);

        sessionService.deleteSession(sessionId);

        verify(sessionRepository, times(1)).deleteById(session.getId());
    }
}
