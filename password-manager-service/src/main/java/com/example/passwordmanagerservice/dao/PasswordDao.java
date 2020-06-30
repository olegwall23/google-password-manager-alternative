package com.example.passwordmanagerservice.dao;

import com.example.passwordmanagerservice.entity.Password;

import java.util.List;

public interface PasswordDao {

    void save(Password password);

    void delete(Password password);

    List<Password> findByAccountId(String accountId);

}
