package ru.vsu.cs.sakovea.api.dto.contestant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.team.TeamDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetContestantDto {

    private int id;
    private String name;
    private String surname;
    private String thirdname;
    private String nickname;
    private String phone;
    private String email;
    private String vk;
    private String telegram;
    private boolean isTeamCreator;
    private TeamDto team;
}
