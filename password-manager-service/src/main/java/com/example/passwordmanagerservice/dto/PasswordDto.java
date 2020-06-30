package com.example.passwordmanagerservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordDto {

    private String website;

    private String username;

    private String password;

}
