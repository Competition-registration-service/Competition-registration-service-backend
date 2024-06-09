package ru.vsu.cs.sakovea.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sakovea.api.dto.*;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;

import java.util.List;

@Tag(name = "Контроллер мероприятий и соревнований", description = "Действия с мероприятиями и соревнованиями")
@RequestMapping("/event")
public interface EventApi {


    @Operation(
            summary = "Получение мероприятия/соревнования",
            description = "Возвращает информацию о мероприятии/соревновании"
    )
    @GetMapping("/{id}")
    ResponseEntity<CompetitionDto> getEvent(
            @PathVariable ("id") int id);


    @Operation(
            summary = "Получение страниц или контента страницы мероприятия или соревнования",
            description = "Возвращает страницы или контент страниц мероприятия или соревнования"
    )
    @GetMapping("/{id}/contents")
    ResponseEntity<List<ContentDto>> getContents(
            @PathVariable ("id") int id);

    @Operation(
            summary = "Получение информации о конкретной странице или контенте",
            description = "Возвращает конкретную информацию о странице или контенте"
    )
    @GetMapping("/content/{id}")
    ResponseEntity<ContentDto> getContent(
            @PathVariable ("id") int id);

    @Operation(
            summary = "Получение полей соревнования",
            description = "Возвращает поля для регистрации в соревновании"
    )
    @GetMapping("/{id}/fields")
    ResponseEntity<List<FieldDto>> getFields(
            @PathVariable ("id") int id);

}
