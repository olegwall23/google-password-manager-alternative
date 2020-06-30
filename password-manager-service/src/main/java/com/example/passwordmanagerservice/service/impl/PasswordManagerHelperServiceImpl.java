package com.example.passwordmanagerservice.service.impl;

import com.example.passwordmanagerservice.dto.PasswordDto;
import com.example.passwordmanagerservice.entity.Password;
import com.example.passwordmanagerservice.service.PasswordManagerHelperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PasswordManagerHelperServiceImpl implements PasswordManagerHelperService {

    @Override
    public Password setKey(Password password) {
        if (password.getKey() == null) {
            password.setKey(password.getWebsite() + "|" + password.getUsername());
        }
        return password;
    }

    @Override
    public Password convertToPasswordEntity(String accountId, PasswordDto password) {
        return Password.builder()
                .accountId(accountId)
                .website(password.getWebsite())
                .username(password.getUsername())
                .password(password.getPassword())
                .build();
    }

    @Override
    public PasswordDto convertToPasswordDto(Password password) {
        PasswordDto passwordDto = new PasswordDto();
        passwordDto.setWebsite(password.getWebsite());
        passwordDto.setUsername(password.getUsername());
        passwordDto.setPassword(password.getPassword());
        return passwordDto;
    }

    @Override
    public List<PasswordDto> convertToPasswordDto(List<Password> passwords) {
        return passwords.stream().map(this::convertToPasswordDto).collect(Collectors.toList());
    }

}
