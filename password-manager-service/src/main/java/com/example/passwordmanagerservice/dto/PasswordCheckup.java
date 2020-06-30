package com.example.passwordmanagerservice.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
public class PasswordCheckup {

    private List<PasswordDto> weekPasswords;

    private List<PasswordDto> reusedPassword;

}
