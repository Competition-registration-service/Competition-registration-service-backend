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

@Tag(name = "Контроллер словаря", description = "Действия со словарем")
@RequestMapping("/ref-value")
public interface RefValueApi {

    @Operation(
            summary = "Получение словаря",
            description = "Возвращает информацию о словаре"
    )
    @GetMapping("/values/{domainCid}")
    ResponseEntity<List<RefValueDto>> getRefValuesByDomainCid(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable ("domainCid") int domainCid);

    @Operation(
            summary = "Получение конкретного значения словаря",
            description = "Возвращает информацию о конкретном значение словаря"
    )
    @GetMapping("/values/{valueCid}")
    ResponseEntity<RefValueDto> getRefValuesByValueCid(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable ("valueCid") String valueCid);
}
