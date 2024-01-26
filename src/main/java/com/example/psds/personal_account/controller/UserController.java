package com.example.psds.personal_account.controller;

import com.example.psds.personal_account.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/group/{groupId}")
    public List<com.example.psds.personal_account.dto.User> getAllUsersFromGroup(@PathVariable Long groupId){
        return userService.getUserList(groupId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{userId}")
    public com.example.psds.personal_account.dto.User getUserByIdAndGroupId(@PathVariable Long userId){
        return userService.getUserByIdAndGroupId(userId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> updateUserByIdAndGroupId(@RequestBody com.example.psds.personal_account.dto.User user){
        userService.updateUser(user);
        return new ResponseEntity<>("Successful update", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@RequestBody com.example.psds.personal_account.dto.User user){
        userService.deleteUser(user);
        return new ResponseEntity<>("Successful delete", HttpStatus.NO_CONTENT);
    }
}
