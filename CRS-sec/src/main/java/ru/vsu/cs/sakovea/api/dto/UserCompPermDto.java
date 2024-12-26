package ru.vsu.cs.sakovea.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;
import ru.vsu.cs.sakovea.api.dto.user.UserDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserCompPermDto {

    private int id;
    private UserDto user;
    private CompetitionDto competition;
    private RefValueDto refRole;
}