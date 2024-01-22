package com.example.psds.personal_account.controller;

import com.example.psds.personal_account.model.Group;
import com.example.psds.personal_account.model.RoleInGroup;
import com.example.psds.personal_account.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    GroupService gs;

    public GroupController(GroupService gs){
        this.gs=gs;
    }

    @GetMapping("")
    public List<Group> getAllGroups(){
        return gs.getGroupList();
    }

    @PostMapping("")
    public void createGroup(@RequestBody String name, @RequestBody String description, @RequestBody List<RoleInGroup> roleInGroups){
        gs.createNewGroup(name, description, roleInGroups);
    }

    @GetMapping("/{groupId}")
    public Group getGroupById(@PathVariable Long groupId){
        return gs.getGroupById(groupId);
    }

    @PutMapping("/{groupId}")
    public void changeGroupById(@PathVariable Long groupId, @RequestBody String name, @RequestBody String description, @RequestBody List<RoleInGroup> roleInGroups){
        if (name==null || description==null || roleInGroups==null) {
            gs.deleteGroup(groupId);
        }else{
            gs.updateGroup(groupId, name, description, roleInGroups);
        }
    }
}
