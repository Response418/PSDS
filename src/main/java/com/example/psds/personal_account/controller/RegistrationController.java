package com.example.psds.personal_account.controller;

import com.example.psds.personal_account.dto.UserRegisterDto;
import com.example.psds.personal_account.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final UserServiceImpl userServiceImpl;

    @PostMapping("")
    public void register(@RequestBody UserRegisterDto userRegisterDto){
        userServiceImpl.save(userRegisterDto);
    }

}
