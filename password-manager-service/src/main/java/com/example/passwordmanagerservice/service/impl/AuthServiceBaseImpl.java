package com.example.passwordmanagerservice.service.impl;

import com.example.passwordmanagerservice.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceBaseImpl implements AuthService {

    public String getAccountId(String token) {
        return "someAccountId";
    }

}
