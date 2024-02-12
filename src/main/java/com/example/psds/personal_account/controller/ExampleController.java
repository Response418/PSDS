package com.example.psds.personal_account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
public class ExampleController {

    @GetMapping
    public String example() {
        return "Hello, world!";
    }

    @GetMapping("/student")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public String exampleUser() {
        return "This student!";
    }

    @GetMapping("/director")
    @PreAuthorize("hasRole('ROLE_DIRECTOR')")
    public String exampleDirector() {
        return "This director!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String exampleAdmin() {
        return "Hello, admin!";
    }

}