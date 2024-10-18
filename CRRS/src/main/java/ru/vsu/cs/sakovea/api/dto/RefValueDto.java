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
    private List<Content> contentPages;
    private List<Content> contentFormats;
    private List<Content> contentLanguages;
    private List<Competition> competitions;
    private List<Competition> compCounts;
    private List<Competition> compAges;
    private List<File> files;
    private List<List<UserCompPerm>> roles;
    private List<List<Field>> fields;
    private List<List<User>> userEducations;
    private List<List<User>> userCourses;
    private List<List<User>> userRegions;
}
