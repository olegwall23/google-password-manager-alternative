package com.example.passwordmanagerservice.service.impl;

import com.example.passwordmanagerservice.dao.PasswordDao;
import com.example.passwordmanagerservice.dto.PasswordCheckup;
import com.example.passwordmanagerservice.dto.PasswordDto;
import com.example.passwordmanagerservice.entity.Password;
import com.example.passwordmanagerservice.service.PasswordCheckupService;
import com.example.passwordmanagerservice.service.PasswordManagerHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PasswordCheckupServiceImpl implements PasswordCheckupService {

    @Autowired
    private PasswordManagerHelperService passwordManagerHelperService;

    @Autowired
    private PasswordDao passwordDao;

    @Override
    public PasswordCheckup passwordCheckup(String accountId) {
        return passwordCheckup(passwordDao.findByAccountId(accountId));
    }

    @Override
    public PasswordCheckup passwordCheckup(List<Password> passwords) {
        return PasswordCheckup.builder()
                .reusedPassword(getReusedPasswords(passwords))
                .weekPasswords(getWeekPasswords(passwords))
                .build();
    }

    @Override
    public List<PasswordDto> getWeekPasswords(List<Password> passwords) {
        List<PasswordDto> weekPasswords = new ArrayList<>();

        passwords.forEach(password -> {
            if (isWeekPassword(password.getPassword())) {
                weekPasswords.add(passwordManagerHelperService.convertToPasswordDto(password));
            }
        });

        return weekPasswords;
    }

    @Override
    public List<PasswordDto> getReusedPasswords(List<Password> passwords) {
        List<PasswordDto> reusedPasswords = new ArrayList<>();

        Set<String> usedPasswords = new HashSet<>(passwords.size());
        passwords.forEach(password -> {
            if (usedPasswords.contains(password.getPassword())) {
                reusedPasswords.add(passwordManagerHelperService.convertToPasswordDto(password));
            } else {
                usedPasswords.add(password.getPassword());
            }
        });

        return reusedPasswords;
    }

    private boolean isWeekPassword(String password) {
        if (password.length() < 8) {
            return true;
        }
        return false;
    }

}
