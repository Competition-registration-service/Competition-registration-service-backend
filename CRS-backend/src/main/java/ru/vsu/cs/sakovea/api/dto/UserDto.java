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

    private boolean surename;

    private String thirdname;

    private Date birthDate;

    private String gender;

    private String login;

    private String password;

    private String phone;

    private String email;

    private String vk;

    private String telegram;

    private Date createDate;

    private Date changeDate;

    private RefValue refEducation;

    private RefValue refCourse;

    private RefValue refRegion;

    private List<Contestant> contestants;

    private List<UserCompPerm> userCompPerms;
}
