package com.example.psds;

import com.example.psds.knowledge_base.service.PlanService;
import com.example.psds.personal_account.dto.moderator.RoleInGroupDto;
import com.example.psds.personal_account.mapper.ModelWithGroupToObjectWithGroup;
import com.example.psds.personal_account.model.*;
import com.example.psds.personal_account.repository.GroupRepository;
import com.example.psds.personal_account.repository.RoleInGroupRepository;
import com.example.psds.personal_account.repository.RoleRepository;
import com.example.psds.personal_account.repository.UserRepository;
import com.example.psds.personal_account.service.MyMapperService;
import com.example.psds.personal_account.service.RelationUsersService;
import com.example.psds.personal_account.service.RoleInGroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RoleInGroupServiceTest {

    @Mock
    private RoleInGroupRepository roleInGroupRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private ModelWithGroupToObjectWithGroup modelWithGroupToObjectWithGroup;

    @Mock
    private RelationUsersService relationUsersService;

    @Mock
    private PlanService planService;

    @Mock
    private MyMapperService myMapperService;

    @InjectMocks
    private RoleInGroupService roleInGroupService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        roleInGroupService = new RoleInGroupService(roleInGroupRepository, userRepository, roleRepository, groupRepository, modelWithGroupToObjectWithGroup, relationUsersService, planService, myMapperService);
    }

    @Test
    public void testCreateRoleInGroup() {
        // Mock input data
        RoleInGroupDto roleInGroupDto = new RoleInGroupDto();
        roleInGroupDto.setUserId(1L);
        roleInGroupDto.setRoleId(2L);
        roleInGroupDto.setGroupId(3L);

        Group group = new Group();
        group.setId(3L);

        User user = new User();
        user.setId(1L);

        Role role = new Role();
        role.setId(2L);

        // Mock repository methods
        when(groupRepository.findById(3L)).thenReturn(Optional.of(group));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(roleRepository.findById(2L)).thenReturn(Optional.of(role));
        when(roleInGroupRepository.existsByUserIdAndRoleIdAndGroupId(1L, 2L, 3L)).thenReturn(false);

        // Call the service method
        ResponseEntity<?> responseEntity = roleInGroupService.create(roleInGroupDto);

        // Verify the repository method calls
        verify(groupRepository, times(1)).findById(3L);
        verify(userRepository, times(1)).findById(1L);
        verify(roleRepository, times(1)).findById(2L);
        verify(roleInGroupRepository, times(1)).existsByUserIdAndRoleIdAndGroupId(1L, 2L, 3L);
        verify(roleInGroupRepository, times(1)).save(any(RoleInGroup.class));

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
