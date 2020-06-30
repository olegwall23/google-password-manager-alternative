package com.example.passwordmanagerservice.service.impl;

import com.example.passwordmanagerservice.dao.PasswordDao;
import com.example.passwordmanagerservice.dto.PasswordDto;
import com.example.passwordmanagerservice.entity.Password;
import com.example.passwordmanagerservice.service.PasswordManagerHelperService;
import com.example.passwordmanagerservice.service.PasswordManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordManagerServiceImpl implements PasswordManagerService {

    @Autowired
    private PasswordDao passwordDao;

    @Autowired
    private PasswordManagerHelperService passwordManagerHelperService;

    @Override
    public void save(Password password) {
        passwordDao.save(passwordManagerHelperService.setKey(password));
    }

    @Override
    public void save(String accountId, PasswordDto passwordDto) {
        save(passwordManagerHelperService.convertToPasswordEntity(accountId, passwordDto));
    }

    @Override
    public void delete(String accountId, String website, String username) {
        passwordDao.delete(passwordManagerHelperService.setKey(
                Password.builder().accountId(accountId)
                        .website(website).username(username).build()));
    }

    @Override
    public List<PasswordDto> findAll(String accountId) {
        return passwordManagerHelperService.convertToPasswordDto(passwordDao.findByAccountId(accountId));
    }

}
