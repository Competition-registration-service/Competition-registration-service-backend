package ru.vsu.cs.sakovea.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.models.*;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CompetitionDto {
    private int id;
    private String name;
    private int maxNumOfTeamMem;
    private int minNumOfTeamMem;
    private Date startDate;
    private Date endDate;
    private String cid;
    private Competition parent;
    private RefValue refCompetition;
    private RefValue refCompCount;
    private RefValue refCompAge;
    private List<Content> contents;
    private List<List<File>> files;
    private List<List<Team>> teams;
    private List<List<UserCompPerm>> userCompPerms;
    private List<List<Field>> fields;
    private List<List<Contestant>> contestants;
    private List<Competition> competitions;
}
