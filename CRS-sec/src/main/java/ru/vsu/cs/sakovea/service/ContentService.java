package ru.vsu.cs.sakovea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.api.dto.content.RequestContentDto;
import ru.vsu.cs.sakovea.api.dto.content.ResponseContentDto;
import ru.vsu.cs.sakovea.exeptions.ThrowMyException;
import ru.vsu.cs.sakovea.mapper.CompetitionMapper;
import ru.vsu.cs.sakovea.mapper.ContentMapper;
import ru.vsu.cs.sakovea.mapper.RefValueMapper;
import ru.vsu.cs.sakovea.models.Competition;
import ru.vsu.cs.sakovea.models.Content;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;
import ru.vsu.cs.sakovea.repository.CompetitionRepository;
import ru.vsu.cs.sakovea.repository.ContentRepository;
import ru.vsu.cs.sakovea.repository.RefValueRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    private final CompetitionRepository competitionRepository;

    private final RefValueRepository refValueRepository;

    private void checkIsUserAdmin(UserDetailsImpl userDetails) {
        if (Boolean.TRUE.equals(userDetails.getUser().isAdmin()) || (userDetails.getUser().getRoles().getFirst().
                getRefRole().getValueCid().equals(refValueRepository.findRefValueByValueCid("ADMIN").getValueCid()))) {
            return;
        }
        throw new ThrowMyException("Доступ запрещён");
    }


    public ResponseContentDto getContent(Integer eventId, Integer id) {
        Content content = contentRepository.findByIdAndCompetition(id, competitionRepository.findById(eventId).get());
        if (content != null) {
            return ContentMapper.INSTANCE.toResponseContentDto(content);
        }
        throw new ThrowMyException("Нет сонтента, тело пустое пришло из БД");
    }

    public Content createContent(UserDetailsImpl userDetails, RequestContentDto contentDto, int competitionId) {
        checkIsUserAdmin(userDetails);
        Content content = new Content();

        if (contentDto != null){
            content.setFeelingContent(contentDto.getFeelingContent());
            content.setRefPage(RefValueMapper.INSTANCE.toRefValue(contentDto.getRefPage()));
            content.setRefFormat(RefValueMapper.INSTANCE.toRefValue(contentDto.getRefFormat()));
            content.setRefLanguage(RefValueMapper.INSTANCE.toRefValue(contentDto.getRefLanguage()));
            contentRepository.save(content);
            Competition competition = competitionRepository.findById(competitionId);
            if (competition != null) {
                competition.getContents().add(content);
                competitionRepository.save(competition);
                content.setCompetition(competition);
                contentRepository.save(content);
            } else throw new ThrowMyException("Мероприятия с таким ID не существует!");
            return content;
        }
        throw new ThrowMyException("Данные отсутствуют");
    }

    public Content updateContent(UserDetailsImpl userDetails, ContentDto contentDto) {
        checkIsUserAdmin(userDetails);
        Content content = new Content();

        if (contentDto.getFeelingContent() != null){
            content.setFeelingContent(contentDto.getFeelingContent());
        }
        if (contentDto.getRefPage() != null){
            content.setRefPage(RefValueMapper.INSTANCE.toRefValue(contentDto.getRefPage()));
        }
        if (contentDto.getRefFormat() != null){
            content.setRefFormat(RefValueMapper.INSTANCE.toRefValue(contentDto.getRefFormat()));
        }
        if (contentDto.getRefLanguage() != null){
            content.setRefLanguage(RefValueMapper.INSTANCE.toRefValue(contentDto.getRefLanguage()));
        }
        return contentRepository.save(content);
    }

    public List<ResponseContentDto> getCompetitionContents(Integer id) {
        return ContentMapper.INSTANCE.toResponseContentDtoList(contentRepository.findByCompetitionId(id));
    }
}
