package ru.vsu.cs.sakovea.api.dto.userCompPerms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetUserCompPermDto {
    private int id;
    private RefValueDto refValue;
}
