package com.example.psds.personal_account.controller;

import com.example.psds.personal_account.dto.GroupDTO;
import com.example.psds.personal_account.dto.UserDTO;
import com.example.psds.personal_account.model.User;
import com.example.psds.personal_account.repository.UserRepository;
import com.example.psds.personal_account.response.GroupResponseBuilder;
import com.example.psds.personal_account.service.GroupService;
import com.example.psds.personal_account.service.RelationUsersService;
import com.example.psds.personal_account.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final RelationUsersService relationUsersService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final GroupResponseBuilder groupResponseBuilder;

    @GetMapping("")
    public ResponseEntity<?> findGroupByUserId(Principal principal) {
        Long userId = userRepository.findByEmail(principal.getName()).orElseThrow().getId();
        return new ResponseEntity<>(groupService.findByUserId(userId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{userId}/moderator")
    public ResponseEntity<?> findGroupUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(groupService.findByUserId(userId), HttpStatus.OK);
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<?> selectGroup(@PathVariable Long groupId, Principal principal) {
        User user = userService.getUserId(principal.getName());
        GroupDTO groups = groupService.selectGroup(groupId, user.getId());
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/moderator/group")
    public ResponseEntity<?> createGroup(@RequestBody @Valid GroupDTO groupDto) {
        groupService.createGroup(groupDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/moderator")
    public ResponseEntity<?> getGroupList(){
        return new ResponseEntity<>(groupService.getGroupList(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/moderator")
    public ResponseEntity<?> updateGroup(@RequestBody GroupDTO group){
        groupService.updateGroup(group);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/moderator/{groupId}")
    public ResponseEntity<?> getGroupById(@PathVariable Long groupId){
        return new ResponseEntity<>(groupService.getGroupById(groupId), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/moderator/{groupId}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long groupId){
        relationUsersService.deleteRelationUsersByGroupId(groupId);
        groupService.deleteGroup(groupId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{groupId}/users/{userId}/mentors")
    public ResponseEntity<?> setMentorByGroupIdAndUserId(@PathVariable Long groupId, @PathVariable Long userId, @RequestBody UserDTO mentor){
        relationUsersService.createRelationUsersByGroupIdAndUserId(groupId, userId, mentor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/masters/students")
    public ResponseEntity<?> getStudentsByMaster(@RequestParam Long userId, @RequestParam Long groupId) {
        return groupResponseBuilder.getStudentsByMaster(userId, groupId);
    }
}
