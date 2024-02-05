package com.example.psds.personal_account.controller;


import com.example.psds.personal_account.service.SessionService;
import com.example.psds.personal_account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;
    private final SessionService sessionService;

    @GetMapping("")
    public void login() {
    }

//    @PostMapping("/session")
//    public void createSession(@RequestBody LoginSessionDto loginSessionDto, HttpServletRequest request){
//        Long userId = userService.getUserId(loginSessionDto.getEmail());
//        sessionService.createSession(userId, request);
//    }


}
