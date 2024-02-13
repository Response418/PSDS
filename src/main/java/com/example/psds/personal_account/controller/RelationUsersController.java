package com.example.psds.personal_account.controller;

import com.example.psds.personal_account.repository.GroupRepository;
import com.example.psds.personal_account.service.GroupService;
import com.example.psds.personal_account.service.RelationUsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequestMapping("api/relation-users")
@RequiredArgsConstructor
public class RelationUsersController {
    private final RelationUsersService relationUsersService;
    
    //Для назначение наставника для студента Руководителем группы
    @GetMapping("")
    public ResponseEntity<?> getListMentorForGroup(HttpServletRequest servletRequest) {//
        return new ResponseEntity<>(relationUsersService.findListMentorForGroup(servletRequest), HttpStatus.OK);
    }
}
