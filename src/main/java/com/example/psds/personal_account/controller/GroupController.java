package com.example.psds.personal_account.controller;


import com.example.psds.personal_account.dto.authentication.GroupsForUserDto;
import com.example.psds.personal_account.repository.UserRepository;
import com.example.psds.personal_account.service.GroupService;
import com.example.psds.personal_account.service.SessionService;
import com.example.psds.personal_account.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final SessionService sessionService;

    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@RequestBody @Valid GroupsForUserDto groupDto) {
        groupService.createGroup(groupDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findByUserId(Principal principal) {
        Long userId = userRepository.findByEmail(principal.getName()).orElseThrow().getId();
        return new ResponseEntity<>(groupService.findByUserId(userId), HttpStatus.OK);
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<?> selectGroup(@PathVariable Long groupId, Principal principal) {
        Long userId = userService.getUserId(principal.getName());
        GroupsForUserDto groups = groupService.selectGroup(groupId, userId);
        sessionService.editGroupInSession(userId, groupId);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

}
