package ru.vsu.cs.sakovea.api.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class ChangePasswordDto {

    private String password;

}
