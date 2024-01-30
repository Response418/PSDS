package com.example.psds.personal_account.service;

import com.example.psds.personal_account.dto.UserDTO;
import com.example.psds.personal_account.mapper.ModelWithUserToObjectWithUser;
import com.example.psds.personal_account.model.User;
import com.example.psds.personal_account.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelWithUserToObjectWithUser modelWithUserToObjectWithUser;

    public UserService(UserRepository userRepository, ModelWithUserToObjectWithUser modelWithUserToObjectWithUser){
        this.userRepository=userRepository;
        this.modelWithUserToObjectWithUser=modelWithUserToObjectWithUser;
    }

    @Transactional
    public List<UserDTO> getUserList(){
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (int i=0; i<users.size(); i++){
            userDTOS.add(modelWithUserToObjectWithUser.modelToObject(users.get(i)));
        }
        return userDTOS;
    }

    @Transactional
    public UserDTO getUserById(Long userId){
        User user = userRepository.findUserById(userId);
        return modelWithUserToObjectWithUser.modelToObject(user);
    }

    @Transactional
    public void changeUser(UserDTO userDTO){
        User user = modelWithUserToObjectWithUser.objectToModel(userDTO);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

}
