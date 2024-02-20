package com.example.psds.personal_account.controller;

import com.example.psds.personal_account.dto.RelationUsersDTO;
import com.example.psds.personal_account.dto.UserDTO;
import com.example.psds.personal_account.repository.GroupRepository;
import com.example.psds.personal_account.service.GroupService;
import com.example.psds.personal_account.service.RelationUsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("api/relation-users")
@RequiredArgsConstructor
public class RelationUsersController {
    private final RelationUsersService relationUsersService;


    @GetMapping("")
    public ResponseEntity<?> getListMentorForGroup(Principal principal) {//
        return new ResponseEntity<>(relationUsersService.findListMentorForGroup(principal), HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public List<RelationUsersDTO> getListRelationByStudentId(
            @PathVariable("studentId") Long studentId
    ){
        return relationUsersService.getListRelationByStudentId(studentId);
    }

    @GetMapping("/{studentId}/{groupId}")
    public RelationUsersDTO getLinkByStudentAndGroup(
            @PathVariable("studentId") Long studentId,
            @PathVariable("groupId") Long groupId
    ){
        return relationUsersService.getLinkByStudentAndGroup(studentId, groupId);
    }
}
