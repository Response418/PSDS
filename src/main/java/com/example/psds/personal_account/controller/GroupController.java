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
}
