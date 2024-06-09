package ru.vsu.cs.sakovea.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sakovea.api.dto.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.FileDto;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;

import java.util.List;

@Tag(name = "Контроллер файлов", description = "Действия с файлами")
@RequestMapping("/file")
public interface FileApi {

    @Operation(
            summary = "Получение файла",
            description = "Возвращает файл"
    )
    @GetMapping("/{id}")
    ResponseEntity<CompetitionDto> getFile(
            @PathVariable ("id") int id);
}
