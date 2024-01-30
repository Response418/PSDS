package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.Role;
import com.example.psds.personal_account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUsersById(Long userId);

    @Query("""
        select
            case
                when count(rig) > 0 then true else false end
            from RoleInGroup rig
                where rig.group.id = :groupId
                and rig.user.id = :userId
    """)
    boolean IfUserBelongGroup(Long userId, Long groupId);

    @Query("""
        select r.roles
            from RoleInGroup r
                where r.user.id = :userId
    """)
    Role getRoleByUserId(Long userId);
}
