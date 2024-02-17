package com.example.psds.personal_account.controller;

import com.example.psds.personal_account.dto.UserDTO;
import com.example.psds.personal_account.service.RelationUsersService;
import com.example.psds.personal_account.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final RelationUsersService relationUsersService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getUserList(){
        return userService.getUserList();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void changeUser(@RequestBody UserDTO userDTO){
        userService.changeUser(userDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId){
        relationUsersService.deleteRelationUsersByUserIdOrMasterId(userId, userId);
        userService.deleteUser(userId);
    }

    @GetMapping("/link/{linkUserId}")
    public Long getUserIdByLink(
        @PathVariable("linkUserId") Long linkUserId
    ) {
        return relationUsersService.getUserIdByLink(linkUserId);
    }
}
