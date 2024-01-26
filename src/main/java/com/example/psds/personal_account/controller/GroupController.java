package com.example.psds.personal_account.controller;

import com.example.psds.personal_account.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService){
        this.groupService=groupService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<com.example.psds.personal_account.dto.Group> getAllGroups(){
        return groupService.getGroupList();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createGroup(@RequestBody com.example.psds.personal_account.dto.Group group){
        groupService.createNewGroup(group);
        return new ResponseEntity<>("Successful create", HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{groupId}")
    public com.example.psds.personal_account.dto.Group getGroupById(@PathVariable Long groupId){
        return groupService.getGroupById(groupId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> updateGroup(@RequestBody com.example.psds.personal_account.dto.Group group){
        groupService.updateGroup(group);
        return new ResponseEntity<>("Successful update", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteGroup(@RequestBody com.example.psds.personal_account.dto.Group group){
        groupService.deleteGroup(group);
        return new ResponseEntity<>("Successful delete", HttpStatus.NO_CONTENT);
    }
}
