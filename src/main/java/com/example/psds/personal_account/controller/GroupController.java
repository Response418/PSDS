package com.example.psds.personal_account.controller;

import com.example.psds.personal_account.dto.GroupDTO;
import com.example.psds.personal_account.dto.UserDTO;
import com.example.psds.personal_account.model.Role;
import com.example.psds.personal_account.service.GroupService;
import com.example.psds.personal_account.service.RelationUsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@AllArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final RelationUsersService relationUsersService;

    @RequestMapping(method = RequestMethod.GET)
    public List<GroupDTO> getGroupList(){
        return groupService.getGroupList();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createGroup(@RequestBody GroupDTO group){
        groupService.createGroup(group);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{groupId}")
    public GroupDTO getGroupById(@PathVariable Long groupId){
        return groupService.getGroupById(groupId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void updateGroup(@RequestBody GroupDTO group){
        groupService.updateGroup(group);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(@PathVariable Long groupId){
        relationUsersService.deleteRelationUsersByGroupId(groupId);
        groupService.deleteGroup(groupId);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{groupId}/users/{userId}/mentors")
    @ResponseStatus(HttpStatus.CREATED)
    public void setMentorByGroupIdAndUserId(@PathVariable Long groupId, @PathVariable Long userId, @RequestBody UserDTO mentor){
        relationUsersService.createRelationUsersByGroupIdAndUserId(groupId, userId, mentor);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{groupId}/users/{userId}/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public void changeRoleInGroupByUserId(@PathVariable Long groupId, @PathVariable Long userId, @RequestBody Role role){
        groupService.changeRoleInGroupByUserId(groupId, userId, role);
    }

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
