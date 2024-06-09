package ru.vsu.cs.sakovea.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsu.cs.sakovea.api.dto.CompetitionDto;

@Tag(name = "Контроллер контента", description = "Действия с контентом")
@RequestMapping("/content")
public interface ContentApi {

    @Operation(
            summary = "Получение контента",
            description = "Возвращает контент"
    )
    @GetMapping("/{id}")
    ResponseEntity<CompetitionDto> getContent(
            @PathVariable("id") int id);
}
