package ru.vsu.cs.sakovea.api.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.sakovea.api.AdminPanelApi;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.mapper.CompetitionMapper;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;
import ru.vsu.cs.sakovea.service.CompetitionService;


@RestController
@AllArgsConstructor
public class AdminController implements AdminPanelApi {

    private final CompetitionService competitionService;
    @Override
    public ResponseEntity<?> createCompetition(HttpServletResponse response, UserDetailsImpl userDetails, CompetitionDto competitionDto) {
        return ResponseEntity.ok(competitionService.createCompetition(competitionDto));
    }

    @Override
    public ResponseEntity<CompetitionDto> updateCompetition(HttpServletResponse response, UserDetailsImpl userDetails, CompetitionDto competitionDto) {
        return ResponseEntity.ok(CompetitionMapper.INSTANCE.toCompetitionDto(competitionService.updateCompetition(competitionDto)));
    }
}
