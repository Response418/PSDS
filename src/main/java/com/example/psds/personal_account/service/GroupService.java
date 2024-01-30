package com.example.psds.personal_account.service;

import com.example.psds.personal_account.dto.GroupDTO;
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
    public List<GroupDTO> getGroupList(){
        List<com.example.psds.personal_account.model.Group> groupModelList = groupRepository.findAll();
        List<GroupDTO> groupObjectList = new ArrayList<>();
        for(int i=0; i<groupModelList.size(); i++){
            groupObjectList.add(modelWithGroupToObjectWithGroup.modelToObject(groupModelList.get(i)));
        }
        return groupObjectList;
    }
    @Transactional
    public void createGroup(GroupDTO groupDTO){
        com.example.psds.personal_account.model.Group groupModel = modelWithGroupToObjectWithGroup.objectToModel(groupDTO);
        groupRepository.save(groupModel);
    }
    @Transactional
    public GroupDTO getGroupById(Long groupId){
        GroupDTO groupObject = modelWithGroupToObjectWithGroup.modelToObject(groupRepository.findGroupById(groupId));
        return groupObject;
    }

    @Transactional
    public void updateGroup(GroupDTO group){
        com.example.psds.personal_account.model.Group groupModel = modelWithGroupToObjectWithGroup.objectToModel(group);
        groupRepository.save(groupModel);
    }

    @Transactional
    public void deleteGroup(Long groupId){
        groupRepository.deleteById(groupId);
    }
}