package com.example.psds.personal_account.controller;

import com.example.psds.personal_account.dto.moderator.RoleInGroupDto;
import com.example.psds.personal_account.service.RoleInGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/roleInGroup")
@RequiredArgsConstructor
public class RoleInGroupController {
    private final RoleInGroupService roleInGroupService;

    @PostMapping("/create")
    public ResponseEntity<?> createRoleInGroup(@RequestBody @Valid RoleInGroupDto roleInGroupDto) {
        roleInGroupService.create(roleInGroupDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}