package ru.vsu.cs.sakovea.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChangeUserRoleDto {
    private String newRole;
    private int competitionId;
}
