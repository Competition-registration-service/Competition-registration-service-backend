package ru.vsu.cs.sakovea.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.sakovea.api.CompetitionApi;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.competition.EventDto;
import ru.vsu.cs.sakovea.api.dto.competition.GetCompetitionDto;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.api.dto.content.ResponseContentDto;
import ru.vsu.cs.sakovea.api.dto.field.ResponseFieldDto;
import ru.vsu.cs.sakovea.api.dto.fieldvalue.RequestFieldValueDto;
import ru.vsu.cs.sakovea.exeptions.CustomException;
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
    public ResponseEntity<?> getEvent(Integer id) {
        try {
            return ResponseEntity.ok(competitionService.getEvent(id));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }

    }

    @Override
    public ResponseEntity<?> getCompetition(Integer eventId, Integer id) {
        try {
            return ResponseEntity.ok(competitionService.getCompetition(eventId, id));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }

    }

    @Override
    public ResponseEntity<?> getAllCompetitions(Integer eventId) {
        try {
            return ResponseEntity.ok(competitionService.getAllCompetition(eventId));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }

    }

    @Override
    public ResponseEntity<?> getEventContent(Integer eventId, Integer id) {
        try {
            return ResponseEntity.ok(contentService.getContent(eventId, id));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }

    }

    @Override
    public ResponseEntity<?> getEventContents(Integer id) {
        try {
            return ResponseEntity.ok(contentService.getCompetitionContents(id));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }

    }

    @Override
    public ResponseEntity<?> getRegistrationPage(Integer id, Integer competitionId) {
        try {
            return ResponseEntity.ok(competitionService.getCompetitionRegistrationPage(id, competitionId));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }

    }

    @Override
    public ResponseEntity<?> registerOnCompetition(Integer id, Integer competitionId,
                                                   UserDetailsImpl userDetails, List<RequestFieldValueDto> requestFieldValueDto) {
        return null;
    }
}
