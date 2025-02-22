package ru.vsu.cs.sakovea.api.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.sakovea.api.AdminPanelApi;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionCreateDto;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.competition.CreateEventDto;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.api.dto.content.RequestContentDto;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueRequestDto;
import ru.vsu.cs.sakovea.api.dto.user.UserDto;
import ru.vsu.cs.sakovea.mapper.ContentMapper;
import ru.vsu.cs.sakovea.models.Competition;
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
    public ResponseEntity<?> createEvent(HttpServletResponse response, UserDetailsImpl userDetails, CreateEventDto eventDto) {
        return ResponseEntity.ok(competitionService.createEvent(userDetails, eventDto));
    }

    @Override
    public ResponseEntity<Competition> updateEvent(HttpServletResponse response, UserDetailsImpl userDetails,
                                                   CompetitionDto competitionDto) {
        return ResponseEntity.ok(competitionService.updateEvent(userDetails, competitionDto));
    }


    /**"Если при создании соревнования выбирается поле "индивидуальное" то поля с максимумом
     и минимумом челов в команде пропадают или наоборот их нет и если выбирается командное то появляются"
     **/
    @Override
    public ResponseEntity<?> createCompetition(HttpServletResponse response, UserDetailsImpl userDetails,
                                               CompetitionCreateDto competitionCreateDto, int eventId) {
        return ResponseEntity.ok(competitionService.createCompetition(userDetails, competitionCreateDto, eventId));
    }

    @Override
    public ResponseEntity<Competition> updateCompetition(HttpServletResponse response, UserDetailsImpl userDetails,
                                                         CompetitionDto competitionDto, int eventId) {
        return ResponseEntity.ok(competitionService.updateCompetition(userDetails, competitionDto));
    }

    @Override
    public ResponseEntity<?> createCompetitionContent(HttpServletResponse response, UserDetailsImpl userDetails,
                                                       RequestContentDto contentDto, int competitionId) {
        return ResponseEntity.ok(contentService.createContent(userDetails, contentDto, competitionId));
    }

    @Override
    public ResponseEntity<ContentDto> updateCompetitionContent(HttpServletResponse response, UserDetailsImpl userDetails,
                                                                ContentDto contentDto, int competitionId) {
        return ResponseEntity.ok(ContentMapper.INSTANCE.toContentDto(contentService.updateContent(userDetails,
                contentDto)));
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers(UserDetailsImpl userDetails, Integer offset, Integer limit) {
        List<UserDto> userDtos = userService.getAllUsersPagination(userDetails, offset, limit);
        return ResponseEntity.ok(userDtos);
    }

    @Override
    public ResponseEntity<?> changeUserRoleOnEvent(UserDetailsImpl userDetails, int eventId, int userId, RefValueRequestDto newRoleDto) {
        userService.changeUserRoleOnEvent(userDetails, eventId, userId, newRoleDto);
        return ResponseEntity.ok("Роль пользователя успешно изменена");
    }

}
