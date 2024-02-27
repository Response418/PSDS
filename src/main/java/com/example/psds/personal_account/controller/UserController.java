package com.example.psds.personal_account.controller;

import com.example.psds.personal_account.dto.UserDTO;
import com.example.psds.personal_account.service.RelationUsersService;
import com.example.psds.personal_account.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("api/moderator/users")
public class UserController {
    private final UserService userService;
    private final RelationUsersService relationUsersService;

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_DIRECTOR')")
    @GetMapping("")
    public ResponseEntity<?> getUserList(){
        return new ResponseEntity<>(userService.getUserList(), HttpStatus.OK);
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


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_DIRECTOR')")
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
