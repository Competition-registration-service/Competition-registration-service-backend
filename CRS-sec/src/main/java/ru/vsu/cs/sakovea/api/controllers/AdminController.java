package ru.vsu.cs.sakovea.api.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.sakovea.api.AdminPanelApi;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.mapper.CompetitionMapper;
import ru.vsu.cs.sakovea.mapper.ContentMapper;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;
import ru.vsu.cs.sakovea.service.CompetitionService;
import ru.vsu.cs.sakovea.service.ContentService;


@RestController
@AllArgsConstructor
public class AdminController implements AdminPanelApi {

    //TODO:
    // Поправить корсы. Поправить ссылку на подтверждение почты.
    // Сделать нормальную обработку ошибок которая будет везде перехватывать и возвращать объект { error: String; code: Integer }
    // Сделать дто для профиля

    private final CompetitionService competitionService;
    private final ContentService contentService;


    @Override
    public ResponseEntity<?> createCompetition(HttpServletResponse response, UserDetailsImpl userDetails, CompetitionDto competitionDto) {
        return ResponseEntity.ok(competitionService.createCompetition(userDetails, competitionDto));
    }

    @Override
    public ResponseEntity<CompetitionDto> updateCompetition(HttpServletResponse response, UserDetailsImpl userDetails, CompetitionDto competitionDto) {
        return ResponseEntity.ok(CompetitionMapper.INSTANCE.toCompetitionDto(competitionService.updateCompetition(userDetails, competitionDto)));
    }

    @Override
    public ResponseEntity<?> createCompetitionContent(HttpServletResponse response, UserDetailsImpl userDetails, ContentDto contentDto) {
        return ResponseEntity.ok(contentService.createContent(userDetails, contentDto));
    }

    @Override
    public ResponseEntity<ContentDto> updateCompetitionContent(HttpServletResponse response, UserDetailsImpl userDetails, ContentDto contentDto) {
        return ResponseEntity.ok(ContentMapper.INSTANCE.toContentDto(contentService.updateContent(userDetails, contentDto)));
    }
}
