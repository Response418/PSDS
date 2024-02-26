package com.example.psds.personal_account.controller;

import com.example.psds.personal_account.dto.moderator.RoleInGroupDto;
import com.example.psds.personal_account.service.RoleInGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/roleInGroup")
@RequiredArgsConstructor
public class RoleInGroupController {
    private final RoleInGroupService roleInGroupService;


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_DIRECTOR')")
    @PostMapping("")
    public ResponseEntity<?> createRoleInGroup(@RequestBody @Valid RoleInGroupDto roleInGroupDto) {
        return roleInGroupService.create(roleInGroupDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_DIRECTOR')")
    @GetMapping("")
    public ResponseEntity<?> getListsForRoleInGroup() {
        return new ResponseEntity<>(roleInGroupService.getListsForRoleInGroup(), HttpStatus.OK);
    }

    @GetMapping("/role")
    public ResponseEntity<?> getRoleStudent() {
        return new ResponseEntity<>(roleInGroupService.getRoleStudent(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_DIRECTOR')")
    @GetMapping("/{groupId}")
    public ResponseEntity<?> getUsersForGroup(@PathVariable Long groupId) {
        return new ResponseEntity<>(
                roleInGroupService.getUsersForGroup(groupId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_DIRECTOR')")
    @DeleteMapping("/{groupId}/{userId}")
    public ResponseEntity<?> deleteUsersForGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        roleInGroupService.deleteUsersForGroup(groupId, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
