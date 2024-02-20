package com.example.psds.personal_account.component;

import com.example.psds.personal_account.model.ERole;
import com.example.psds.personal_account.model.Role;
import com.example.psds.personal_account.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        initializeRoles();
    }

    private void initializeRoles() {
        for (ERole role : ERole.values()) {
            try {
                if (roleRepository.findByName(role) == null) {
                    Role newRole = new Role();
                    newRole.setName(role);
                    roleRepository.save(newRole);
                }
            }catch (IncorrectResultSizeDataAccessException e){

            }
        }
    }
}
