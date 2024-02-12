package com.example.psds.personal_account.repository;

import com.example.psds.personal_account.model.RelationUsers;
import com.example.psds.personal_account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RelationUsersRepository extends JpaRepository<RelationUsers, Long> {
    @Query("""
        select ru
            from RelationUsers ru
                where ru.group.id = :groupId
                and ru.master.id = :masterId
    """)
    List<RelationUsers> findAllByMasterIdAndGroupId(Long masterId, Long groupId);
}
