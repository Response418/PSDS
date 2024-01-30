package com.example.psds.personal_account.controller;
import com.example.psds.personal_account.model.Role;
import com.example.psds.personal_account.response.GroupResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
public class GroupController {

    private final GroupResponseBuilder groupResponseBuilder;

    @GetMapping("/master/student")
    public ResponseEntity<?> getStudentsByMaster(
        @RequestHeader("userId") Long userId,
        @RequestHeader("groupId") Long groupId
    ) {
        return groupResponseBuilder.getStudentsByMaster(userId, groupId);
    }

}
