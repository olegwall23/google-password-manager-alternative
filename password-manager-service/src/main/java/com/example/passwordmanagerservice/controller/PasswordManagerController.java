package com.example.passwordmanagerservice.controller;

import com.example.passwordmanagerservice.dto.PasswordDto;
import com.example.passwordmanagerservice.service.AuthService;
import com.example.passwordmanagerservice.service.PasswordManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class PasswordManagerController {

    @Autowired
    private PasswordManagerService passwordManagerService;

    @Autowired
    private AuthService authService;

    @PostMapping("/password")
    public Mono<ResponseEntity<?>> addPassword(@RequestHeader String token, @RequestBody PasswordDto passwordDto) {
        passwordManagerService.save(authService.getAccountId(token), passwordDto);
        return Mono.just(ResponseEntity.ok("ok"));
    }

    @GetMapping("/password")
    public List<PasswordDto> getPassword(@RequestHeader String token) {
        return passwordManagerService.findAll(authService.getAccountId(token));
    }

}
