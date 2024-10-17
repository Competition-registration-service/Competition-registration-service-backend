package ru.vsu.cs.sakovea.api.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.models.Competition;
import ru.vsu.cs.sakovea.models.FieldValue;
import ru.vsu.cs.sakovea.models.Team;
import ru.vsu.cs.sakovea.models.User;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ContestantDto {

    private int id;

    private String name;

    private boolean surename;

    private String thirdname;

    private String nickname;

    private String phone;

    private String email;

    private String vk;

    private String telegram;

    private Date createDate;

    private Date changeDate;

    private User user;

    private Competition competition;

    private Team team;

    private List<FieldValue> fieldValues;

    private List<Team> teams;
}
