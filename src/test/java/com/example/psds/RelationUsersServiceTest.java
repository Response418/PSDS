package com.example.psds;

import com.example.psds.personal_account.dto.RelationUsersDTO;
import com.example.psds.personal_account.dto.UserDTO;
import com.example.psds.personal_account.mapper.ModelRelationUsersToObjectRelationUsers;
import com.example.psds.personal_account.model.*;
import com.example.psds.personal_account.repository.*;
import com.example.psds.personal_account.service.RelationUsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RelationUsersServiceTest {

    @Mock
    private RelationUsersRepository relationUsersRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelRelationUsersToObjectRelationUsers modelRelationUsersToObjectRelationUsers;

    @InjectMocks
    private RelationUsersService relationUsersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRelationUsersByGroupIdAndUserId() {
        Long groupId = 1L;
        Long userId = 2L;
        UserDTO mentor = new UserDTO();

        RelationUsers existingRelationUsers = new RelationUsers();
        existingRelationUsers.setMaster(new User());
        when(relationUsersRepository.findRelationUsersByGroup_IdAndStudent_Id(groupId, userId))
                .thenReturn(existingRelationUsers);

        relationUsersService.createRelationUsersByGroupIdAndUserId(groupId, userId, mentor);

        verify(relationUsersRepository, times(1)).save(any(RelationUsers.class));
    }

    @Test
    void testGetRelationUserByUserId() {
        Long userId = 1L;
        RelationUsers relationUsers = new RelationUsers();
        when(relationUsersRepository.findByStudentId(userId)).thenReturn(relationUsers);

        RelationUsers result = relationUsersService.getRelationUserByUserId(userId);

        assertEquals(relationUsers, result);
    }

    @Test
    void testCreateRelationUser() {
        RelationUsers relationUsers = new RelationUsers();

        relationUsersService.createRelationUser(relationUsers);

        verify(relationUsersRepository, times(1)).save(relationUsers);
    }

    @Test
    void testDeleteRelationUsersByGroupId() {
        Long groupId = 1L;
        RelationUsers relationUsers = new RelationUsers();
        when(relationUsersRepository.findRelationUsersByGroup_Id(groupId)).thenReturn(relationUsers);

        relationUsersService.deleteRelationUsersByGroupId(groupId);

        verify(relationUsersRepository, times(1)).delete(relationUsers);
    }

    @Test
    void testDeleteRelationUsersByGroupId_noRelationUsers() {
        Long groupId = 1L;
        when(relationUsersRepository.findRelationUsersByGroup_Id(groupId)).thenReturn(null);

        relationUsersService.deleteRelationUsersByGroupId(groupId);

        verify(relationUsersRepository, never()).delete(any(RelationUsers.class));
    }

    @Test
    void testDeleteRelationUsersByUserIdOrMasterId() {
        Long userId = 1L;
        Long masterId = 2L;
        RelationUsers relationUsers = new RelationUsers();
        when(relationUsersRepository.findRelationUsersByStudent_IdOrMaster_Id(userId, masterId))
                .thenReturn(relationUsers);

        relationUsersService.deleteRelationUsersByUserIdOrMasterId(userId, masterId);

        verify(relationUsersRepository, times(1)).delete(relationUsers);
    }

    @Test
    void testDeleteRelationUsersByUserIdOrMasterId_noRelationUsers() {
        Long userId = 1L;
        Long masterId = 2L;
        when(relationUsersRepository.findRelationUsersByStudent_IdOrMaster_Id(userId, masterId))
                .thenReturn(null);

        relationUsersService.deleteRelationUsersByUserIdOrMasterId(userId, masterId);

        verify(relationUsersRepository, never()).delete(any(RelationUsers.class));
    }

    @Test
    void testGetUserIdByLink() {
        Long linkUserId = 1L;
        Long studentId = 2L;
        when(relationUsersRepository.getStudentIdById(linkUserId)).thenReturn(studentId);

        Long result = relationUsersService.getUserIdByLink(linkUserId);

        assertEquals(studentId, result);
    }

    @Test
    void testGetListRelationByStudentId() {
        Long studentId = 1L;
        User student = new User();
        student.setId(studentId);
        when(userRepository.findUserById(studentId)).thenReturn(student);

        List<RelationUsers> relationUsers = new ArrayList<>();
        when(relationUsersRepository.findAllByStudent(student)).thenReturn(relationUsers);

        List<RelationUsersDTO> relationUsersDTOs = new ArrayList<>();
        when(modelRelationUsersToObjectRelationUsers.modelToObject(any(RelationUsers.class)))
                .thenReturn(new RelationUsersDTO());

        List<RelationUsersDTO> result = relationUsersService.getListRelationByStudentId(studentId);

        assertEquals(relationUsersDTOs, result);
    }

    @Test
    void testGetLinkByStudentAndGroup() {
        Long studentId = 1L;
        Long groupId = 2L;
        RelationUsers relationUsers = new RelationUsers();
        when(relationUsersRepository.findRelationUsersByGroup_IdAndStudent_Id(groupId, studentId))
                .thenReturn(relationUsers);

        RelationUsersDTO relationUsersDTO = new RelationUsersDTO();
        when(modelRelationUsersToObjectRelationUsers.modelToObject(relationUsers)).thenReturn(relationUsersDTO);

        RelationUsersDTO result = relationUsersService.getLinkByStudentAndGroup(studentId, groupId);

        assertEquals(relationUsersDTO, result);
    }
}