package com.example.psds.personal_account.controller;

import com.example.psds.personal_account.dto.GroupDTO;
import com.example.psds.personal_account.service.GroupService;
import com.example.psds.personal_account.service.RelationUsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;
    private final RelationUsersService relationUsersService;

    public GroupController(GroupService groupService, final RelationUsersService relationUsersService){
        this.groupService=groupService;
        this.relationUsersService = relationUsersService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GroupDTO> getGroupList(){
        return groupService.getGroupList();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createGroup(@RequestBody GroupDTO group){
        groupService.createGroup(group);
        return new ResponseEntity<>("Successful create", HttpStatus.CREATED);
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
}
