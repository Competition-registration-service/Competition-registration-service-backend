package ru.vsu.cs.sakovea.api.dto.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegistrationDto {

    private int id;

    private String name;

    private String surname;

    private String thirdname;

    private String birthDate;

    private String gender;

    private String login;

    private String password;

    private String phone;

    private String email;
}
