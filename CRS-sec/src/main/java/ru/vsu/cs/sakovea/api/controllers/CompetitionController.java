package ru.vsu.cs.sakovea.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.sakovea.api.CompetitionApi;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.competition.EventDto;
import ru.vsu.cs.sakovea.api.dto.competition.GetCompetitionDto;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
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
    public ResponseEntity<EventDto> getEvent(Integer id) {
        EventDto eventDto = competitionService.getEvent(id);
        return ResponseEntity.ok(eventDto);
    }

    @Override
    public ResponseEntity<GetCompetitionDto> getCompetition(Integer id) {
        GetCompetitionDto getCompetitionDto = competitionService.getCompetition(id);
        return ResponseEntity.ok(getCompetitionDto);
    }

    @Override
    public ResponseEntity<List<GetCompetitionDto>> getAllCompetitions(Integer eventId) {
        List<GetCompetitionDto> competitionDtoList = competitionService.getAllCompetition(eventId);
        return ResponseEntity.ok(competitionDtoList);
    }

    @Override
    public ResponseEntity<ContentDto> getEventContent(Integer id) {
        ContentDto contentDto = contentService.getContent(id);
        return ResponseEntity.ok(contentDto);
    }
}
