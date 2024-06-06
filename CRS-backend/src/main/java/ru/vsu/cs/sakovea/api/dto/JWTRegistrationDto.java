package ru.vsu.cs.sakovea.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTRegistrationDto {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String login;
    private String password;
}
