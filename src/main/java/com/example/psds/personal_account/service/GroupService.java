package com.example.psds.personal_account.service;

import com.example.psds.personal_account.model.Group;
import com.example.psds.personal_account.model.RoleInGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private com.example.psds.personal_account.repository.GroupRepository gr;
    @Transactional
    public List<Group> getGroupList(){
        return gr.findAll();
    }
    @Transactional
    public void createNewGroup(String name, String description, List<RoleInGroup> roleInGroup){
        Group group = new Group();
        //group.setId();
        group.setName(name);
        group.setDescription(description);
        group.setRoleInGroups(roleInGroup);
    }
    @Transactional
    public Group getGroupById(Long groupId){
        return gr.findGroupById(groupId);
    }

    @Transactional
    public void updateGroup(Long groupId, String name, String description, List<RoleInGroup> roleInGroup){
        Group group = gr.findGroupById(groupId);
        group.setName(name);
        group.setDescription(description);
        group.setRoleInGroups(roleInGroup);
    }

    @Transactional
    public void deleteGroup(Long groupId){
        gr.deleteById(groupId);
    }
}