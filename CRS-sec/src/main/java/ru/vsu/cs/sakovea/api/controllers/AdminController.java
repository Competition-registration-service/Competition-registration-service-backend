package ru.vsu.cs.sakovea.api.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.sakovea.api.AdminPanelApi;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.api.dto.user.UserDto;
import ru.vsu.cs.sakovea.mapper.CompetitionMapper;
import ru.vsu.cs.sakovea.mapper.ContentMapper;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;
import ru.vsu.cs.sakovea.service.CompetitionService;
import ru.vsu.cs.sakovea.service.ContentService;
import ru.vsu.cs.sakovea.service.UserService;

import java.util.List;


@RestController
@AllArgsConstructor
public class AdminController implements AdminPanelApi {

    //TODO:
    // Поправить корсы. Поправить ссылку на подтверждение почты.
    // Сделать нормальную обработку ошибок которая будет везде перехватывать и возвращать объект { error: String; code: Integer }

    private final CompetitionService competitionService;
    private final ContentService contentService;
    private final UserService userService;


    @Override
    public ResponseEntity<?> createCompetition(HttpServletResponse response, UserDetailsImpl userDetails, @Valid CompetitionDto competitionDto) {
        return ResponseEntity.ok(competitionService.createCompetition(userDetails, competitionDto));
    }

    @Override
    public ResponseEntity<CompetitionDto> updateCompetition(HttpServletResponse response, UserDetailsImpl userDetails,@Valid CompetitionDto competitionDto) {
        return ResponseEntity.ok(CompetitionMapper.INSTANCE.toCompetitionDto(competitionService.updateCompetition(userDetails, competitionDto)));
    }

    @Override
    public ResponseEntity<?> createCompetitionContent(HttpServletResponse response, UserDetailsImpl userDetails,@Valid ContentDto contentDto) {
        return ResponseEntity.ok(contentService.createContent(userDetails, contentDto));
    }

    @Override
    public ResponseEntity<ContentDto> updateCompetitionContent(HttpServletResponse response, UserDetailsImpl userDetails,@Valid ContentDto contentDto) {
        return ResponseEntity.ok(ContentMapper.INSTANCE.toContentDto(contentService.updateContent(userDetails, contentDto)));
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers(UserDetailsImpl userDetails, Integer offset, Integer limit) {
        List<UserDto> userDtos = userService.getAllUsersPagination(userDetails, offset, limit);
        return ResponseEntity.ok(userDtos);
    }

}
