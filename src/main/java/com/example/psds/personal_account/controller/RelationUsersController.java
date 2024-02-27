package com.example.psds.personal_account.controller;

import com.example.psds.personal_account.dto.MentorDTO;
import com.example.psds.personal_account.dto.RelationUsersDTO;
import com.example.psds.personal_account.service.RelationUsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("api/relation-users")
@RequiredArgsConstructor
public class RelationUsersController {
    private final RelationUsersService relationUsersService;

    @GetMapping("")
    public ResponseEntity<?> getListMentorForGroup(Principal principal, @RequestParam Long groupId) {
        return new ResponseEntity<>(relationUsersService.
                findListMentorForGroup(principal, groupId), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> editMentorForStudent(@RequestBody @Valid MentorDTO mentorDTO) {
        relationUsersService.editMentorForGroup(mentorDTO.getMentorId(), mentorDTO.getRelationId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public List<RelationUsersDTO> getListRelationByStudentId(@PathVariable("studentId") Long studentId){
        return relationUsersService.getListRelationByStudentId(studentId);
    }

    @GetMapping("/{studentId}/{groupId}")
    public RelationUsersDTO getLinkByStudentAndGroup(@PathVariable("studentId") Long studentId,
            @PathVariable("groupId") Long groupId){
        return relationUsersService.getLinkByStudentAndGroup(studentId, groupId);
    }
}
