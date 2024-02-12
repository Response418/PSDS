package com.example.psds.personal_account.service;

import com.example.psds.personal_account.model.ERole;
import com.example.psds.personal_account.repository.UserRepository;
import com.example.psds.personal_account.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public Long getUserId(String email) {
        return userRepository.findByEmail(email).orElseThrow().getId();
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
import com.example.psds.personal_account.dto.UserDTO;
import com.example.psds.personal_account.mapper.ModelWithUserToObjectWithUser;
import com.example.psds.personal_account.model.User;
import com.example.psds.personal_account.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelWithUserToObjectWithUser modelWithUserToObjectWithUser;

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
