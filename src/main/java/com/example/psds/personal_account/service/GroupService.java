package com.example.psds.personal_account.service;

import com.example.psds.personal_account.mapper.ModelWithGroupToObjectWithGroup;
import com.example.psds.personal_account.repository.GroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
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
    public List<com.example.psds.personal_account.object.Group> getGroupList(){
        Iterator<com.example.psds.personal_account.model.Group> groupModelList = groupRepository.findAll().iterator();
        List<com.example.psds.personal_account.object.Group> groupObjectList = new ArrayList<>();
        com.example.psds.personal_account.model.Group groupModel;
        while ((groupModel=groupModelList.next())!=null){
            groupObjectList.add(modelWithGroupToObjectWithGroup.modelToObject(groupModel));
        }
        return groupObjectList;
    }
    @Transactional
    public void createNewGroup(com.example.psds.personal_account.object.Group group){
        com.example.psds.personal_account.model.Group groupModel = modelWithGroupToObjectWithGroup.objectToModel(group);
        groupRepository.save(groupModel);
    }
    @Transactional
    public com.example.psds.personal_account.object.Group getGroupById(Long groupId){
        com.example.psds.personal_account.object.Group groupObject = modelWithGroupToObjectWithGroup.modelToObject(groupRepository.findGroupById(groupId));
        return groupObject;
    }

    @Transactional
    public void updateGroup(com.example.psds.personal_account.object.Group group){
        com.example.psds.personal_account.model.Group groupModel = modelWithGroupToObjectWithGroup.objectToModel(group);
        groupRepository.save(groupModel);
    }

    @Transactional
    public void deleteGroup(com.example.psds.personal_account.object.Group group){
        com.example.psds.personal_account.model.Group groupModel = modelWithGroupToObjectWithGroup.objectToModel(group);
        groupRepository.delete(groupModel);
    }
}