package com.example.psds.personal_account.controller;

import com.example.psds.personal_account.dto.GroupDTO;
import com.example.psds.personal_account.dto.UserDTO;
import com.example.psds.personal_account.dto.authentication.GroupsForUserDto;
import com.example.psds.personal_account.model.Role;
import com.example.psds.personal_account.repository.UserRepository;
import com.example.psds.personal_account.response.GroupResponseBuilder;
import com.example.psds.personal_account.service.GroupService;
import com.example.psds.personal_account.service.RelationUsersService;
import com.example.psds.personal_account.service.SessionService;
import com.example.psds.personal_account.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final RelationUsersService relationUsersService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final SessionService sessionService;
    private final GroupResponseBuilder groupResponseBuilder;

    @GetMapping("")
    public ResponseEntity<?> findGroupByUserId(Principal principal) {
        Long userId = userRepository.findByEmail(principal.getName()).orElseThrow().getId();
        return new ResponseEntity<>(groupService.findByUserId(userId), HttpStatus.OK);
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<?> selectGroup(@PathVariable Long groupId, Principal principal) {
        Long userId = userService.getUserId(principal.getName());
        GroupDTO groups = groupService.selectGroup(groupId, userId);
        sessionService.editGroupInSession(userId, groupId);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @PostMapping("/moderator/group")
    public ResponseEntity<?> createGroup(@RequestBody @Valid GroupDTO groupDto) {
        groupService.createGroup(groupDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, path = "/moderator")
    public List<GroupDTO> getGroupList(){
        return groupService.getGroupList();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/moderator")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateGroup(@RequestBody GroupDTO group){
        groupService.updateGroup(group);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/moderator/{groupId}")
    public GroupDTO getGroupById(@PathVariable Long groupId){

        return groupService.getGroupById(groupId);
    }



    @RequestMapping(method = RequestMethod.DELETE, path = "/moderator/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(@PathVariable Long groupId){
        relationUsersService.deleteRelationUsersByGroupId(groupId);
        groupService.deleteGroup(groupId);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{groupId}/users/{userId}/mentors")
    @ResponseStatus(HttpStatus.CREATED)
    public void setMentorByGroupIdAndUserId(@PathVariable Long groupId, @PathVariable Long userId, @RequestBody UserDTO mentor){
        relationUsersService.createRelationUsersByGroupIdAndUserId(groupId, userId, mentor);
    }

//    @RequestMapping(method = RequestMethod.PUT, path = "/{groupId}/users/{userId}/roles")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void changeRoleInGroupByUserId(@PathVariable Long groupId, @PathVariable Long userId, @RequestBody Role role){
//        //groupService.changeRoleInGroupByUserId(groupId, userId, role);
//    }



    @GetMapping("/masters/students")
    public ResponseEntity<?> getStudentsByMaster(
            HttpServletRequest request
    ) {
        Cookie[] cookies = request.getCookies();
        String sessionId = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionId".equals(cookie.getName())) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }
        return groupResponseBuilder.getStudentsByMaster(sessionId);
    }

}
