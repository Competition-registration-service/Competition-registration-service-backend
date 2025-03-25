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
import ru.vsu.cs.sakovea.api.dto.field.CreateFieldDto;
import ru.vsu.cs.sakovea.api.dto.user.ChangeUserRoleDto;
import ru.vsu.cs.sakovea.api.dto.user.GetUserDto;
import ru.vsu.cs.sakovea.api.dto.user.GetUserForAdminDto;
import ru.vsu.cs.sakovea.api.dto.user.UserDto;
import ru.vsu.cs.sakovea.exeptions.CustomException;
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
        try {
            return ResponseEntity.ok(competitionService.createEvent(userDetails, eventDto));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }
    }

    @Override
    public ResponseEntity<?> updateEvent(HttpServletResponse response, UserDetailsImpl userDetails,
                                                   CompetitionDto competitionDto) {
        try {
            return ResponseEntity.ok(competitionService.updateEvent(userDetails, competitionDto));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }

    }


    /**"Если при создании соревнования выбирается поле "индивидуальное" то поля с максимумом
     и минимумом челов в команде пропадают или наоборот их нет и если выбирается командное то появляются"
     **/
    @Override
    public ResponseEntity<?> createCompetition(HttpServletResponse response, UserDetailsImpl userDetails,
                                               CompetitionCreateDto competitionCreateDto, int eventId) {
        try {
            return ResponseEntity.ok(competitionService.createCompetition(userDetails, competitionCreateDto, eventId));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }
    }

    @Override
    public ResponseEntity<?> updateCompetition(HttpServletResponse response, UserDetailsImpl userDetails,
                                                         CompetitionDto competitionDto, int eventId) {
        try {
            return ResponseEntity.ok(competitionService.updateCompetition(userDetails, competitionDto));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }

    }

    @Override
    public ResponseEntity<?> createCompetitionContent(HttpServletResponse response, UserDetailsImpl userDetails,
                                                       RequestContentDto contentDto, int competitionId) {
        try {
            return ResponseEntity.ok(contentService.createContent(userDetails, contentDto, competitionId));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }

    }

    @Override
    public ResponseEntity<?> updateCompetitionContent(HttpServletResponse response, UserDetailsImpl userDetails,
                                                                ContentDto contentDto, int competitionId) {
        try {
            return ResponseEntity.ok(ContentMapper.INSTANCE.toContentDto(contentService.updateContent(userDetails,
                    contentDto)));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }

    }

    @Override
    public ResponseEntity<?> getAllUsers(UserDetailsImpl userDetails, Integer offset, Integer limit) {
        try {
            return ResponseEntity.ok(userService.getAllUsersPagination(userDetails, offset, limit));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }

    }

    @Override
    public ResponseEntity<?> getUserForAdmin(UserDetailsImpl userDetails, int userId) {
        try {
            return ResponseEntity.ok(userService.getUserForAdmin(userDetails, userId));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }

    }

    @Override
    public ResponseEntity<?> changeUserRole(UserDetailsImpl userDetails, int userId, ChangeUserRoleDto userRoleDto) {
        try {
            return ResponseEntity.ok(userService.changeUserRole(userDetails, userId, userRoleDto));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }

    }

    @Override
    public ResponseEntity<?> createCompetitionRegistrationPage(HttpServletResponse response, UserDetailsImpl userDetails,
                                                               CreateFieldDto createFieldDto, Integer id, Integer competitionId) {
        try {
            return ResponseEntity.ok(competitionService.createCompetitionRegistrationPage(userDetails, createFieldDto, id, competitionId));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }
    }

    @Override
    public ResponseEntity<?> getRefValuesAdmin(UserDetailsImpl userDetails) {
        try {
            return ResponseEntity.ok(competitionService.getRefValues(userDetails));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }
    }

}
