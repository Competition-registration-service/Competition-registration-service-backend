package ru.vsu.cs.sakovea.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.contestant.ContestantDto;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;
import ru.vsu.cs.sakovea.api.dto.userCompPerms.UserCompPermDto;
import ru.vsu.cs.sakovea.models.UserCompPerm;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {

    private int id;
    private String name;
    private String surname;
    private String thirdname;
    private String birthDate;
    private String gender;
    private String login;
    private String password; // todo пароль захеширован
    private String phone;
    private String email;
    private String vk;
    private String telegram;
    private Timestamp createDate;
    private Timestamp changeDate;
    private RefValueDto refEducation;
    private RefValueDto refCourse;
    private RefValueDto refRegion;
    private List<UserCompPermDto> rolesValueCid;
}
