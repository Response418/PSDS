package com.example.psds.personal_account.component;

import com.example.psds.personal_account.model.*;
import com.example.psds.personal_account.repository.RoleRepository;
import com.example.psds.personal_account.repository.UserRepository;
import com.example.psds.personal_account.service.GroupService;
import com.example.psds.personal_account.service.RoleInGroupService;
import com.example.psds.personal_account.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleInGroupService roleInGroupService;
    private final RoleRepository roleRepository;
    private final GroupService groupService;

    @Value("${admin.email}")
    private String email;

    @Value("${admin.password}")
    private String password;

    @PostConstruct
    public void init() {
        initializeAdmin();
    }

    private void initializeAdmin() {
        if(!userRepository.existsByEmail(email)) {
            var admin = User.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .role(ERole.ROLE_ADMIN)
                    .build();
            userService.create(admin);

            Group group = new Group();
            group.setName("Группа администраторов");
            groupService.save(group);

            Role role = roleRepository.findByName(ERole.ROLE_ADMIN);
            RoleInGroup roleInGroup = new RoleInGroup();
            roleInGroup.setUser(admin);
            roleInGroup.setRole(role);
            roleInGroup.setGroup(group);
            roleInGroupService.save(roleInGroup);
        }
    }
}
