package ru.vsu.cs.sakovea.api.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.models.Competition;
import ru.vsu.cs.sakovea.models.RefValue;
import ru.vsu.cs.sakovea.models.User;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserCompPermDto {

    private int id;

    private User user;

    private Competition competition;

    private RefValue refRole;
}
