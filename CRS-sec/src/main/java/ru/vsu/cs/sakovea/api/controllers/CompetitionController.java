package ru.vsu.cs.sakovea.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import ru.vsu.cs.sakovea.api.CompetitionApi;
import ru.vsu.cs.sakovea.api.dto.competition.EventDto;
import ru.vsu.cs.sakovea.api.dto.competition.GetCompetitionDto;
import ru.vsu.cs.sakovea.api.dto.content.ResponseContentDto;
import ru.vsu.cs.sakovea.api.dto.field.ResponseFieldDto;
import ru.vsu.cs.sakovea.api.dto.fieldvalue.RequestFieldValueDto;
import ru.vsu.cs.sakovea.api.dto.team.GetTeamDto;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;
import ru.vsu.cs.sakovea.service.CompetitionService;
import ru.vsu.cs.sakovea.service.ContentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompetitionController implements CompetitionApi {
    private final CompetitionService competitionService;
    private final ContentService contentService;

    @Override
    public ResponseEntity<EventDto> getEvent(Integer id, UserDetailsImpl userDetails) {
            return ResponseEntity.ok(competitionService.getEvent(id, userDetails));
    }

    @Override
    public ResponseEntity<GetCompetitionDto> getCompetition(Integer eventId, Integer id, UserDetailsImpl userDetails) {
            return ResponseEntity.ok(competitionService.getCompetition(eventId, id, userDetails));

    }

    @Override
    public ResponseEntity<List<GetCompetitionDto>> getAllCompetitions(Integer eventId) {
            return ResponseEntity.ok(competitionService.getAllCompetition(eventId));
    }

    @Override
    public ResponseEntity<ResponseContentDto> getEventContent(Integer eventId, Integer id) {
            return ResponseEntity.ok(contentService.getContent(eventId, id));

    }

    @Override
    public ResponseEntity<List<ResponseContentDto>> getEventContents(Integer id) {
            return ResponseEntity.ok(contentService.getCompetitionContents(id));
    }

    @Override
    public ResponseEntity<List<ResponseFieldDto>> getRegistrationPage(Integer id, Integer competitionId) {
            return ResponseEntity.ok(competitionService.getCompetitionRegistrationPage(id, competitionId));
    }

    @Override
    public ResponseEntity<Boolean> registerOnSingleCompetition(Integer id, Integer competitionId, UserDetailsImpl userDetails, List<RequestFieldValueDto> requestFieldValueDto) {
            return ResponseEntity.ok(competitionService.registerOnCompetition(id, competitionId, userDetails, requestFieldValueDto));
    }

    @Override
    public ResponseEntity<String> checkTeamAccessCode(Integer id,  UserDetailsImpl userDetails, Integer competitionId, String teamCode) {
            return ResponseEntity.ok(competitionService.checkAccessCode(id, userDetails, competitionId, teamCode));
    }

    @Override
    public ResponseEntity<GetTeamDto> registerOnTeamCompetition(Integer id, Integer competitionId, UserDetailsImpl userDetails, List<RequestFieldValueDto> requestFieldValueDto) {
            return ResponseEntity.ok(competitionService.registerOnTeamCompetition(id, competitionId, userDetails, requestFieldValueDto));
    }

    @Override
    public ResponseEntity<GetTeamDto> capitanRegisterOnTeamCompetition(Integer id, Integer competitionId, UserDetailsImpl userDetails, List<RequestFieldValueDto> requestFieldValueDto) {
            return ResponseEntity.ok(competitionService.capitanRegisterOnTeamCompetition(id, competitionId, userDetails, requestFieldValueDto));
    }

    @Override
    public ResponseEntity<?> getHistoryOfEventAndCompetitions(UserDetailsImpl userDetails, Integer offset, Integer limit) {
        return ResponseEntity.ok(competitionService.getHistoryOfEventsAndCompetitions(userDetails, offset, limit));
    }
}
