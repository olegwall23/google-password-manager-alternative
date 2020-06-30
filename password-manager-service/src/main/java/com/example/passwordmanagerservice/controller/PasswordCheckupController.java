package com.example.passwordmanagerservice.controller;

import com.example.passwordmanagerservice.dto.PasswordCheckup;
import com.example.passwordmanagerservice.service.AuthService;
import com.example.passwordmanagerservice.service.PasswordCheckupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PasswordCheckupController {

    @Autowired
    private PasswordCheckupService passwordCheckupService;

    @Autowired
    private AuthService authService;

    @GetMapping("/password-checkup")
    public Mono<PasswordCheckup> passwordCheckup(@RequestHeader String token) {
        return Mono.just(passwordCheckupService.passwordCheckup(authService.getAccountId(token)));
    }

}
