package com.example.psds.personal_account.service;

import com.example.psds.personal_account.mapper.ModelWithGroupToObjectWithGroup;
import com.example.psds.personal_account.repository.GroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final ModelWithGroupToObjectWithGroup modelWithGroupToObjectWithGroup;
    public GroupService(GroupRepository groupRepository, ModelWithGroupToObjectWithGroup modelWithGroupToObjectWithGroup){
        this.groupRepository=groupRepository;
        this.modelWithGroupToObjectWithGroup=modelWithGroupToObjectWithGroup;
    }
    @Transactional
    public List<com.example.psds.personal_account.dto.Group> getGroupList(){
        List<com.example.psds.personal_account.model.Group> groupModelList = groupRepository.findAll();
        List<com.example.psds.personal_account.dto.Group> groupObjectList = new ArrayList<>();
        for(int i=0; i<groupModelList.size(); i++){
            groupObjectList.add(modelWithGroupToObjectWithGroup.modelToObject(groupModelList.get(i)));
        }
        return groupObjectList;
    }
    @Transactional
    public void createNewGroup(com.example.psds.personal_account.dto.Group group){
        com.example.psds.personal_account.model.Group groupModel = modelWithGroupToObjectWithGroup.objectToModel(group);
        groupRepository.save(groupModel);
    }
    @Transactional
    public com.example.psds.personal_account.dto.Group getGroupById(Long groupId){
        com.example.psds.personal_account.dto.Group groupObject = modelWithGroupToObjectWithGroup.modelToObject(groupRepository.findGroupById(groupId));
        return groupObject;
    }

    @Transactional
    public void updateGroup(com.example.psds.personal_account.dto.Group group){
        com.example.psds.personal_account.model.Group groupModel = modelWithGroupToObjectWithGroup.objectToModel(group);
        groupRepository.save(groupModel);
    }

    @Transactional
    public void deleteGroup(com.example.psds.personal_account.dto.Group group){
        com.example.psds.personal_account.model.Group groupModel = modelWithGroupToObjectWithGroup.objectToModel(group);
        groupRepository.delete(groupModel);
    }
}