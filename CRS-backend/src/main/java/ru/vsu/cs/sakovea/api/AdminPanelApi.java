package ru.vsu.cs.sakovea.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sakovea.api.dto.*;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;

import java.util.List;

@Tag(name = "Контроллер фдминистратора", description = "Действия проводимые администратором")
@RequestMapping("/admin-panel")
public interface AdminPanelApi {
    @Operation(
            summary = "Создание мероприятия",
            description = "Создаёт новое мероприятие"
    )
    @PostMapping("/create-event")
    ResponseEntity<CompetitionDto> createEvent(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CompetitionDto competitionDto
    );

    @Operation(
            summary = "Создание соревнования",
            description = "Создаёт новое соревнование в рамках мероприятия"
    )
    @PostMapping("/event/{parent_id}/create-competition")
    ResponseEntity<CompetitionDto> createCompetition(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("parent_id") int parentId,
            @RequestBody CompetitionDto competitionDto
    );

    @Operation(
            summary = "Редактирование мероприятия/соревнования",
            description = "Обновляет значение мероприятия/соревнования"
    )
    @PutMapping("/event/{id}/update")
    ResponseEntity<CompetitionDto> updateEvent(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CompetitionDto competitionDto,
            @PathVariable("id") int id);

    @Operation(
            summary = "Добавление файла",
            description = "Добавляет новый файл"
    )
    @PutMapping("/file/{id}/add-file")
    ResponseEntity<CompetitionDto> addFile(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable ("id") int id,
            @RequestBody FileDto fileDto
    );

    @Operation(
            summary = "Добавление файла",
            description = "Добавляет новый файл"
    )
    @PutMapping("/competition/{id}/add-field")
    ResponseEntity<CompetitionDto> addField(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable ("id") int id,
            @RequestBody FieldDto fieldDto
    );

    @Operation(
            summary = "Создание списка полей",
            description = "Создаёт список полей"
    )
    @PostMapping("/create-fields")
    ResponseEntity<List<FieldDto>> createFields(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody List<FieldDto> fieldDtos
    );

    @Operation(
            summary = "Получение списка полей",
            description = "Возвращает список полей"
    )
    @GetMapping("/fields")
    ResponseEntity<List<FieldDto>> getFields(
            @AuthenticationPrincipal UserDetailsImpl userDetails);

    @Operation(
            summary = "Редактирование списка полей",
            description = "Обновляет список полей"
    )
    @PutMapping("/update-fields")
    ResponseEntity<List<FieldDto>> updateFields(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody List<FieldDto> fieldDtos);

    @Operation(
            summary = "Редактирование значения словаря",
            description = "Обновляет значение словаря"
    )
    @PutMapping("/values/{valueCid}/update")
    ResponseEntity<RefValueDto> updateRefValuesByValueCid(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody RefValueDto refValueDto);

    @Operation(
            summary = "Удаление значения из словаря",
            description = "Удаляет значение и ничего не возвращает"
    )
    @DeleteMapping("/values/{valueCid}/delete")
    ResponseEntity<Void> deleteRefValueByValueCid(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable ("valueCid") String valueCid);

    @Operation(
            summary = "Создание файла",
            description = "Создаёт файл"
    )
    @PostMapping("/create-file")
    ResponseEntity<FileDto> createFile(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody FileDto fileDto
    );

    @Operation(
            summary = "Редактирование файла",
            description = "Обновляет файл"
    )
    @PutMapping("/file/{id}/update")
    ResponseEntity<FileDto> updateFile(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable ("id") int id,
            @RequestBody FileDto fileDto);

    @Operation(
            summary = "Удаление файла",
            description = "Удаляет файл и ничего не возвращает"
    )
    @DeleteMapping("/file/{id}/delete")
    ResponseEntity<Void> deleteFile(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable ("id") int id);

    @Operation(
            summary = "Создание контента",
            description = "Создаёт контент"
    )
    @PostMapping("/create-content")
    ResponseEntity<ContentDto> createContent(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody ContentDto contentDto
    );

    @Operation(
            summary = "Редактирование контента",
            description = "Обновляет контент"
    )
    @PutMapping("/content/{id}/update")
    ResponseEntity<ContentDto> updateContent(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable ("id") int id,
            @RequestBody ContentDto contentDto);

    @Operation(
            summary = "Удаление контента",
            description = "Удаляет контент и ничего не возвращает"
    )
    @DeleteMapping("/content/{id}/delete")
    ResponseEntity<Void> deleteContent(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable ("id") int id);

    @Operation(
            summary = "Создание конкретного поля",
            description = "Создаёт конкретное поле"
    )
    @PostMapping("/create-field-value")
    ResponseEntity<FieldValueDto> createFieldValue(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody FieldValueDto fieldValueDto
    );

    @Operation(
            summary = "Редактирование конкретного поля",
            description = "Обновляет конкретное поле"
    )
    @PutMapping("/field-value/{id}/update")
    ResponseEntity<FieldValueDto> updateFieldValue(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable ("id") int id,
            @RequestBody FieldValueDto fieldValueDto);

    @Operation(
            summary = "Удаление конкретного поля",
            description = "Удаляет конкретное поле и ничего не возвращает"
    )
    @DeleteMapping("/field-value/{id}/delete")
    ResponseEntity<Void> deleteFieldValue(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable ("id") int id);
}
