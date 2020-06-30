package com.example.passwordmanagerservice.service;

import com.example.passwordmanagerservice.dto.PasswordDto;
import com.example.passwordmanagerservice.entity.Password;

import java.util.List;

public interface PasswordManagerHelperService {

    Password setKey(Password password);

    Password convertToPasswordEntity(String accountId, PasswordDto password);

    PasswordDto convertToPasswordDto(Password password);

    List<PasswordDto> convertToPasswordDto(List<Password> passwords);

}
