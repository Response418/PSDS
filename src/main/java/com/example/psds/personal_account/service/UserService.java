package com.example.psds.personal_account.service;

import com.example.psds.personal_account.model.Role;
import com.example.psds.personal_account.model.User;
import com.example.psds.personal_account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean hasUserById(Long userId){
        User user = getUserById(userId);
        return (user != null);
    }

    public User getUserById(Long userId){
        return userRepository.findUsersById(userId);
    }

    public boolean IfUserBelongGroup(Long userId, Long groupId){
        return userRepository.IfUserBelongGroup(userId, groupId);
    }

    public boolean ifRoleAccessByUserId(Long userId, Role role){
        Role userRole = userRepository.getRoleByUserId(userId);
        return (role == userRole);
    }
}
