package com.example.psds.personal_account.service;

import com.example.psds.personal_account.mapper.MapperRole;
import com.example.psds.knowledge_base.model.Plan;
import com.example.psds.knowledge_base.service.PlanService;
import com.example.psds.personal_account.dto.*;
import com.example.psds.personal_account.dto.moderator.RoleInGroupDto;
import com.example.psds.personal_account.mapper.ModelWithGroupToObjectWithGroup;
import com.example.psds.personal_account.model.*;
import com.example.psds.personal_account.repository.GroupRepository;
import com.example.psds.personal_account.repository.RoleInGroupRepository;
import com.example.psds.personal_account.repository.RoleRepository;
import com.example.psds.personal_account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RoleInGroupService {
    private final RoleInGroupRepository roleInGroupRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;
    private final ModelWithGroupToObjectWithGroup modelWithGroupToObjectWithGroup;
    private final RelationUsersService relationUsersService;
    private final PlanService planService;
    private final MyMapperService myMapperService;
    private final MapperRole mapperRole;
    private final UserService userService;

    @Value("${admin.email}")
    private String email;


    public void save(RoleInGroup roleInGroup){
        roleInGroupRepository.save(roleInGroup);
    }

    public ResponseEntity<?> create(RoleInGroupDto roleInGroupDto) {
        Group group = groupRepository.findById(roleInGroupDto.getGroupId()).orElseThrow();
        User user = userRepository.findById(roleInGroupDto.getUserId()).orElseThrow();
        Role role = roleRepository.findById(roleInGroupDto.getRoleId()).orElseThrow();

        boolean userWithRoleIdExists = roleInGroupRepository.
                existsByUserIdAndRoleIdAndGroupId(user.getId(),role.getId(),
                        group.getId());
        if(userWithRoleIdExists){
            MessageResponse messageResponse = myMapperService.
                    editStringToMessageResponse(user.getLastName() + " " + user.getFirstName() + " " +
                            user.getFatherName() + " в группе " +
                                    group.getName() + " с ролью " + role.getName() +
                            " уже существует");
            return new ResponseEntity<>(messageResponse, HttpStatus.OK);
        }

        RoleInGroup roleInGroup = new RoleInGroup();
        roleInGroup.setGroup(group);
        roleInGroup.setUser(user);
        roleInGroup.setRole(role);
        log.info("Saving new role in group");
        roleInGroupRepository.save(roleInGroup);

        if(String.valueOf(role.getName()).equals("ROLE_STUDENT")){
            RelationUsers relationUsers = relationUsersService.getRelationUserByUserIdAndGroupId(user.getId(), group.getId());
            if(relationUsers == null){
                RelationUsers relation = new RelationUsers();
                relation.setGroup(group);
                relation.setStudent(user);
                log.info("Saving new relation user for the student");
                relationUsersService.createRelationUser(relation);

                Plan plan = new Plan();
                plan.setRelationUsersId(relation.getId());
                log.info("Saving the student plan for the student");
                planService.createPlan(plan);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public ListRoleInGroupDTO getListsForRoleInGroup() {
        Long userId = userService.getUserId(email).getId();
        Long groupId = roleInGroupRepository.findByUserId(userId).get(0).getGroup().getId();
        List<Group> group = groupRepository.findAllExceptGroupId(groupId);
        List<GroupDTO> groupDTO = new ArrayList<>();
        for (Group group1 : group) {
            groupDTO.add(modelWithGroupToObjectWithGroup.modelToObject(group1));
        }
        List<Role> roles = roleRepository.findAll();
        ListRoleInGroupDTO list = new ListRoleInGroupDTO();
        list.setUserList(userRepository.findListUserForRoleInGroup(userId));
        list.setGroupList(groupDTO);
        list.setRoleList(returnRoleDto(roles));
        return list;
    }

    private List<RoleDto> returnRoleDto(List<Role> roles){
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role role : roles) {
            RoleDto roleDto = new RoleDto();
            switch (String.valueOf(role.getName())) {
                case "ROLE_STUDENT":
                    roleDto.setId(role.getId());
                    roleDto.setName("Студент");
                    roleDtos.add(roleDto);
                    break;
                case "ROLE_MENTOR":
                    roleDto.setId(role.getId());
                    roleDto.setName("Наставник");
                    roleDtos.add(roleDto);
                    break;
                case "ROLE_DIRECTOR":
                    roleDto.setId(role.getId());
                    roleDto.setName("Руководитель группы");
                    roleDtos.add(roleDto);
                    break;
            }
        }
        return roleDtos;
    }

    public List<UserForGroupDTO> getUsersForGroup(Long groupId) {
        List<UserProjection> users = new ArrayList<>(roleInGroupRepository.findUsersByGroupId(groupId));
        List<UserForGroupDTO> userDTO = new ArrayList<>(users.size());
        for (UserProjection user : users) {
            userDTO.add(new UserForGroupDTO(user));
        }
        return userDTO;
    }

    public RoleDto getRoleStudent() {
        Role role = roleRepository.findByName(ERole.ROLE_STUDENT);
        return mapperRole.modelToObject(role);
    }

    public void deleteUsersForGroup(Long groupId, Long userId) {
        roleInGroupRepository.deleteByGroupIdAndUserId(groupId, userId);
        relationUsersService.deleteByStudentIdAndGroupId(userId, groupId);
    }






}
