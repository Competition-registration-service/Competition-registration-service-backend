package ru.vsu.cs.sakovea.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
public class RegistrationDto {

    private int id;

    private String name;

    private String surename;

    private String thirdname;

    private Date birthDate;

    private String gender;

    private String login;

    private String password;

    private String phone;

    private String email;
}
