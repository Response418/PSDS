package com.example.psds.personal_account.service;

import com.example.psds.personal_account.mapper.ModelWithUserToObjectWithUser;
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
    public List<com.example.psds.personal_account.dto.User> getUserList(Long groupId){
        List<com.example.psds.personal_account.model.User> userModelList = userRepository.findAllByRoleInGroups_GroupId(groupId);
        List<com.example.psds.personal_account.dto.User> userObjectList = new ArrayList<>();
        for (int i=0; i<userModelList.size(); i++){
            userObjectList.add(modelWithUserToObjectWithUser.modelToObject(userModelList.get(i)));
        }
        return userObjectList;
    }

    @Transactional
    public com.example.psds.personal_account.dto.User getUserByIdAndGroupId(Long userId){
        com.example.psds.personal_account.model.User userModel = userRepository.findUserById(userId);
        return modelWithUserToObjectWithUser.modelToObject(userModel);
    }

    @Transactional
    public void updateUser(com.example.psds.personal_account.dto.User user){
        com.example.psds.personal_account.model.User userModel = modelWithUserToObjectWithUser.objectToModel(user);
        userRepository.save(userModel);
    }

    @Transactional
    public void deleteUser(com.example.psds.personal_account.dto.User user){
        com.example.psds.personal_account.model.User userModel = modelWithUserToObjectWithUser.objectToModel(user);
        userRepository.delete(userModel);
    }

}
