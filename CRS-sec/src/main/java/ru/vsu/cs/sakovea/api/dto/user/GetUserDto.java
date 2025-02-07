package ru.vsu.cs.sakovea.api.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.UserCompPermDto;
import ru.vsu.cs.sakovea.api.dto.contestant.ContestantDto;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetUserDto {

    private int id;
    private String name;
    private String surname;
    private String thirdname;
    private String birthDate;
    private String login;
    private String phone;
    private String email;
    private String vk;
    private String telegram;
    private RefValueDto refEducation;
    private RefValueDto refCourse;
    private RefValueDto refRegion;
}
