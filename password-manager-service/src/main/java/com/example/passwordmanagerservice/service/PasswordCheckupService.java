package com.example.passwordmanagerservice.service;

import com.example.passwordmanagerservice.dto.PasswordCheckup;
import com.example.passwordmanagerservice.dto.PasswordDto;
import com.example.passwordmanagerservice.entity.Password;

import java.util.List;

public interface PasswordCheckupService {

    PasswordCheckup passwordCheckup(String accountId);

    PasswordCheckup passwordCheckup(List<Password> passwords);

    List<PasswordDto> getWeekPasswords(List<Password> passwords);

    List<PasswordDto> getReusedPasswords(List<Password> passwords);

}
