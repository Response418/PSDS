package com.example.psds.personal_account.service;

import com.example.psds.personal_account.model.RoleInGroup;
import com.example.psds.personal_account.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private com.example.psds.personal_account.repository.UserRepository ur;

    @Transactional
    public List<User> getUserList(Long groupId){
        return ur.findAllByRoleInGroups_GroupId(groupId);
    }

    @Transactional
    public User getUserByIdAndGroupId(Long userId,Long groupId){
        return ur.findUserByIdAndRoleInGroups_GroupId(userId, groupId);
    }

    @Transactional
    public void updateUser(Long userId, Long groupId, Set<Role> roles){
        User user = ur.findUserByIdAndRoleInGroups_GroupId(userId, groupId);
        List<RoleInGroup> roleInGroups = user.getRoleInGroups();
        RoleInGroup roleInGroup;
        while ((roleInGroup = roleInGroups.iterator().next())!=null){
            roleInGroup.setRoles(roles);
        }
    }

    @Transactional
    public void deleteUser(Long userId){
        ur.deleteById(userId);
    }

}
