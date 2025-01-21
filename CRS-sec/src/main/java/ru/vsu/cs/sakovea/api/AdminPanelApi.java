package ru.vsu.cs.sakovea.api;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.api.dto.user.UserDto;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;


@RequestMapping("/admin-panel")
public interface AdminPanelApi {

    @PostMapping("/competition/create")
    ResponseEntity<?> createCompetition(HttpServletResponse response,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails,
                                       @RequestBody CompetitionDto competitionDto);

    @PutMapping("/competition/update")
    ResponseEntity<CompetitionDto> updateCompetition(HttpServletResponse response,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails,
                                       @RequestBody CompetitionDto competitionDto);

    @PostMapping("/competition/content/create")
    ResponseEntity<?> createCompetitionContent(HttpServletResponse response,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @RequestBody ContentDto contentDto);

    @PutMapping("/competition/content/update")
    ResponseEntity<ContentDto> updateCompetitionContent(HttpServletResponse response,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @RequestBody ContentDto contentDto);
}
