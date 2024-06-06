package ru.vsu.cs.sakovea.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Контроллер пользователя", description = "Действия с профилем")
@RequestMapping("/event")
public interface EventApi {
}
