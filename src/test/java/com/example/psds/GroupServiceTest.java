package com.example.psds;

import com.example.psds.personal_account.dto.GroupDTO;
import com.example.psds.personal_account.mapper.ModelWithGroupToObjectWithGroup;
import com.example.psds.personal_account.model.ERole;
import com.example.psds.personal_account.model.Group;
import com.example.psds.personal_account.model.Role;
import com.example.psds.personal_account.model.RoleInGroup;
import com.example.psds.personal_account.repository.GroupRepository;
import com.example.psds.personal_account.repository.RelationUsersRepository;
import com.example.psds.personal_account.repository.RoleInGroupRepository;
import com.example.psds.personal_account.service.GroupService;
import com.example.psds.personal_account.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private RoleInGroupRepository roleInGroupRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private GroupService groupService;

    @Mock
    private ModelWithGroupToObjectWithGroup modelWithGroupToObjectWithGroup;

    @Mock
    private RelationUsersRepository relationUsersRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateGroup() {
        // Arrange
        GroupDTO groupDTO = new GroupDTO();
        Group groupModel = new Group();
        when(modelWithGroupToObjectWithGroup.objectToModel(groupDTO)).thenReturn(groupModel);

        // Act
        groupService.createGroup(groupDTO);

        // Assert
        verify(groupRepository, times(1)).save(groupModel);
    }

    @Test
    public void testFindByUserId() {
        Long userId = 1L;

        RoleInGroup roleInGroup = new RoleInGroup();
        roleInGroup.setGroup(new Group());
        roleInGroup.getGroup().setId(1L);

        List<RoleInGroup> roleInGroups = new ArrayList<>();
        roleInGroups.add(roleInGroup);

        when(roleInGroupRepository.findByUserId(userId)).thenReturn(roleInGroups);
        when(groupRepository.findById(1L)).thenReturn(java.util.Optional.of(new Group()));

        List<GroupDTO> groupsDto = groupService.findByUserId(userId);

        assertEquals(1, groupsDto.size());
        verify(roleInGroupRepository, times(1)).findByUserId(userId);
        verify(groupRepository, times(1)).findById(1L);
    }
    @Test
    void testSelectGroup() {
        Long groupId = 1L;
        Long userId = 1L;

        List<RoleInGroup> roleInGroups = new ArrayList<>();
        RoleInGroup roleInGroup = new RoleInGroup();
        Role role = new Role();
        role.setName(ERole.ROLE_DIRECTOR);
        roleInGroup.setRole(role);
        roleInGroups.add(roleInGroup);

        when(roleInGroupRepository.findByGroupIdAndUserId(groupId, userId)).thenReturn(roleInGroups);
        when(groupRepository.findById(groupId)).thenReturn(java.util.Optional.of(new Group()));
        when(modelWithGroupToObjectWithGroup.modelToObject(any(Group.class))).thenReturn(new GroupDTO());

        GroupDTO result = groupService.selectGroup(groupId, userId);

        verify(userService, times(1)).editRoleUser(userId, ERole.ROLE_DIRECTOR.toString());
        assertEquals(GroupDTO.class, result.getClass());
    }

    @Test
    void testGetGroupList() {
        List<com.example.psds.personal_account.model.Group> groupModelList = new ArrayList<>();
        groupModelList.add(new com.example.psds.personal_account.model.Group());

        when(groupRepository.findAll()).thenReturn(groupModelList);
        when(modelWithGroupToObjectWithGroup.modelToObject(any(com.example.psds.personal_account.model.Group.class))).thenReturn(new GroupDTO());

        List<GroupDTO> result = groupService.getGroupList();

        assertEquals(1, result.size());
        assertEquals(GroupDTO.class, result.get(0).getClass());
    }
    @Test
    void testGetGroupById() {
        Long groupId = 1L;

        when(groupRepository.findGroupById(groupId)).thenReturn(new com.example.psds.personal_account.model.Group());
        when(modelWithGroupToObjectWithGroup.modelToObject(any(com.example.psds.personal_account.model.Group.class))).thenReturn(new GroupDTO());

        GroupDTO result = groupService.getGroupById(groupId);
        assertEquals(GroupDTO.class, result.getClass());
    }

    @Test
    public void testUpdateGroup() {
        // Arrange
        GroupDTO groupDTO = new GroupDTO();
        Group groupModel = new Group();
        when(modelWithGroupToObjectWithGroup.objectToModel(groupDTO)).thenReturn(groupModel);

        // Act
        groupService.updateGroup(groupDTO);

        // Assert
        verify(groupRepository, times(1)).save(groupModel);
    }

    @Test
    void testDeleteGroup() {
        Long groupId = 1L;

        groupService.deleteGroup(groupId);

        verify(groupRepository, times(1)).deleteById(groupId);
    }

    @Test
    void testGetStudentsByMaster() {
        Long masterId = 1L;
        Long groupId = 1L;

        groupService.getStudentsByMaster(masterId, groupId);

        verify(relationUsersRepository, times(1)).findAllByMasterIdAndGroupId(masterId, groupId);
    }
}