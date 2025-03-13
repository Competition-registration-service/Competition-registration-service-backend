package ru.vsu.cs.sakovea.api.dto.fieldvalue;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.TeamDto;
import ru.vsu.cs.sakovea.api.dto.contestant.ContestantDto;
import ru.vsu.cs.sakovea.api.dto.field.FieldDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RequestFieldValueDto {

    private String value;
    private FieldDto field;


    // todo сделать через юзкейсы с проверкой реф тайп. В реф тайп сделай еще записи по полям контестанта и команды.
    // из токена (юзер детайлс) брать юзера и совать в контестанта при его создании.
}
