package com.example.psds.personal_account.controller.authentication;

import com.example.psds.personal_account.dto.authentication.SignInRequest;
import com.example.psds.personal_account.dto.authentication.SignUpRequest;

import com.example.psds.personal_account.service.authentication.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }


    @PostMapping("/logout")
    public ResponseEntity<?> signOut() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

