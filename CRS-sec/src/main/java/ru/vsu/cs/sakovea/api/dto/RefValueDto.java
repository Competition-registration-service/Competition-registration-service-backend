package ru.vsu.cs.sakovea.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.models.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RefValueDto {

    private int id;
    private int domainCid;
    private String valueCid;
    private String shortValue;
    private String longValue;
    private boolean visible;
    private String comment;
    private List<CompetitionDto> competitionsTypes;
    private List<CompetitionDto> competitionsCounts;
    private List<CompetitionDto> competitionsAges;
    private List<ContentDto> contentPages;
    private List<ContentDto> contentFormats;
    private List<ContentDto> contentLanguages;
    private List<FieldDto> fields;
    private List<FileDto> files;
    private List<UserDto> userEducations;
    private List<UserDto> userCourses;
    private List<UserDto> userRegions;
    private List<UserCompPermDto> roles;
}
