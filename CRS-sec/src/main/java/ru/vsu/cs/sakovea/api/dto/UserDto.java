package ru.vsu.cs.sakovea.api.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.models.Contestant;
import ru.vsu.cs.sakovea.models.RefValue;
import ru.vsu.cs.sakovea.models.UserCompPerm;

import java.util.Date;
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
    private String password;
    private String phone;
    private String email;
    private String vk;
    private String telegram;
    private Date createDate;
    private Date changeDate;
    private String activeCode;
    private boolean isAdmin;
    private List<ContestantDto> contestants;
    private RefValueDto refEducation;
    private RefValueDto refCourse;
    private RefValueDto refRegion;
    private List<UserCompPermDto> roles;
}
