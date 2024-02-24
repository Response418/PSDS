package com.example.psds.personal_account.controller;

import com.example.psds.personal_account.dto.moderator.RoleInGroupDto;
import com.example.psds.personal_account.service.RoleInGroupService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/roleInGroup")
@RequiredArgsConstructor
public class RoleInGroupController {
    private final RoleInGroupService roleInGroupService;

    @PostMapping("")
    public ResponseEntity<?> createRoleInGroup(@RequestBody @Valid RoleInGroupDto roleInGroupDto) {
        return roleInGroupService.create(roleInGroupDto);
    }

    @GetMapping("")
    @Transactional
    public ResponseEntity<?> getListsForRoleInGroup() {
        return new ResponseEntity<>(roleInGroupService.getListsForRoleInGroup(), HttpStatus.OK);
    }
}
