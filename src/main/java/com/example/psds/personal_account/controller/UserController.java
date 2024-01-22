package com.example.psds.personal_account.controller;

import com.example.psds.personal_account.model.User;
import com.example.psds.personal_account.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService us;

    public UserController(UserService us){
        this.us=us;
    }

    @GetMapping("/{groupId}")
    public List<User> getAllUsersFromGroup(@PathVariable Long groupId){
        return us.getUserList(groupId);
    }

    @GetMapping("/{groupId}/{userId}")
    public User getUserByIdAndGroupId(@PathVariable Long groupId, @PathVariable Long userId){
        return us.getUserByIdAndGroupId(userId, groupId);
    }

    @PutMapping("/{groupId}/{userId}")
    public void changeUserByIdAndGroupId(@PathVariable Long groupId, @PathVariable Long userId, @RequestBody Set<Role> roles){
        if (true){
            us.updateUser(userId, groupId, roles);
        }else{
            us.deleteUser(userId);
        }
    }
}
