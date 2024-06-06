package ru.vsu.cs.sakovea.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sakovea.api.dto.RefValueDto;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;

import java.util.List;

@Tag(name = "Контроллер пользователя", description = "Действия с профилем")
@RequestMapping("/ref-value")
public interface RefValueApi {

    @Operation(
            summary = "Получение словаря",
            description = "Возвращает информацию о словаре"
    )
    @GetMapping("/values/{domainCid}")
    ResponseEntity<List<RefValueDto>> getRefValuesByDomainCid(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                              @PathVariable int domainCid);

    @Operation(
            summary = "Получение конкретного значения словаря",
            description = "Возвращает информацию о конкретном значение словаря"
    )
    @GetMapping("/values/{valueCid}")
    ResponseEntity<RefValueDto> getRefValuesByValueCid(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                       @PathVariable String valueCid);

    @Operation(
            summary = "Редактирование значения словаря",
            description = "Обновляет значение словаря"
    )
    @PostMapping("/values/{valueCid}/update")
    ResponseEntity<RefValueDto> updateRefValuesByValueCid(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                          @RequestBody RefValueDto refValueDto);

    @Operation(
            summary = "Удаление объявления",
            description = "Удаляет объявление и ничего не возвращает"
    )
    @DeleteMapping("/values/{valueCid}/delete")
    ResponseEntity<Void> deleteRefValueByValueCid(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable String valueCid);
}
