package com.example.psds;

import com.example.psds.personal_account.model.*;
import com.example.psds.personal_account.repository.GroupRepository;
import com.example.psds.personal_account.repository.RelationUsersRepository;
import com.example.psds.personal_account.repository.RoleInGroupRepository;
import com.example.psds.personal_account.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@SpringBootTest
class PsdsApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private RelationUsersRepository relationUsersRepository;
    @Autowired
    private RoleInGroupRepository roleInGroupRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    public void createUserAndMasterAndGroupWithRelationUsers(){
        Group group = new Group();
        group.setName("Группа 1");
        group.setDescription("Иванов-Петров");
        group = groupRepository.save(group);

        User user = new User();
        user.setLastName("Петров");
        user.setFirstName("Иван");
        user.setFatherName("Сергеевич");
        user = userRepository.save(user);

        RoleInGroup roleInGroup = new RoleInGroup();
        roleInGroup.setUser(user);
        roleInGroup.setGroup(group);
        roleInGroup.setRoles(Collections.singleton(Role.ROLE_STUDENT));
        roleInGroupRepository.save(roleInGroup);

        RelationUsers relationUsers = new RelationUsers();
        relationUsers.setStudent(user);
        user = new User();
        user.setLastName("Иванов");
        user.setFirstName("Петр");
        user.setFatherName("Васильевич");
        user = userRepository.save(user);

        roleInGroup = new RoleInGroup();
        roleInGroup.setUser(user);
        roleInGroup.setGroup(group);
        roleInGroup.setRoles(Collections.singleton(Role.ROLE_MENTOR));
        roleInGroupRepository.save(roleInGroup);

        relationUsers.setMaster(user);
        relationUsers.setGroup(group);
        relationUsersRepository.save(relationUsers);
    }

}
