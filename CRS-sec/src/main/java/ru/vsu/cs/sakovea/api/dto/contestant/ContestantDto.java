package ru.vsu.cs.sakovea.api.dto.contestant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.fieldvalue.FieldValueDto;
import ru.vsu.cs.sakovea.api.dto.team.TeamDto;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.user.UserDto;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ContestantDto {

    private int id;
    private String name;
    private String surname;
    private String thirdname;
    private String nickname;
    private String phone;
    private String email;
    private String vk;
    private String telegram;
    private Timestamp createDate;
    private Timestamp changeDate;
    private boolean isTeamCreator;
    private UserDto user;
    private CompetitionDto competition;
    private TeamDto team;
    private List<FieldValueDto> fieldValues;
}
