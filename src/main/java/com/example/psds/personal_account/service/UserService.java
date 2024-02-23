package com.example.psds.personal_account.service;

import com.example.psds.personal_account.dto.UserDTO;
import com.example.psds.personal_account.mapper.ModelWithUserToObjectWithUser;
import com.example.psds.personal_account.model.ERole;
import com.example.psds.personal_account.model.Role;
import com.example.psds.personal_account.repository.UserRepository;
import com.example.psds.personal_account.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelWithUserToObjectWithUser modelWithUserToObjectWithUser;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getUserId(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public void editRoleUser(Long userId, String nameRole) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setRole(ERole.valueOf(nameRole));
        save(user);
    }

    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        return save(user);
    }

    public User getByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public List<UserDTO> getUserList(){
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (int i=0; i<users.size(); i++){
            userDTOS.add(modelWithUserToObjectWithUser.modelToObject(users.get(i)));
        }
        return userDTOS;
    }

    public UserDTO getUserById(Long userId){
        User user = userRepository.findUserById(userId);
        return modelWithUserToObjectWithUser.modelToObject(user);
    }

    public void changeUser(UserDTO userDTO){
        User user = modelWithUserToObjectWithUser.objectToModel(userDTO);
        userRepository.save(user);
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
}
