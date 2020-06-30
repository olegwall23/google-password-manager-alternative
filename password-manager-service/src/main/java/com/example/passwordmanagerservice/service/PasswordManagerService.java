package com.example.passwordmanagerservice.service;

import com.example.passwordmanagerservice.dto.PasswordDto;
import com.example.passwordmanagerservice.entity.Password;

import java.util.List;

public interface PasswordManagerService {

    void save(Password password);

    void save(String accountId, PasswordDto passwordDto);

    void delete(String accountId, String website, String username);

    List<PasswordDto> findAll(String accountId);

}
