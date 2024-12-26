package ru.vsu.cs.sakovea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.mapper.CompetitionMapper;
import ru.vsu.cs.sakovea.mapper.ContentMapper;
import ru.vsu.cs.sakovea.mapper.RefValueMapper;
import ru.vsu.cs.sakovea.models.Competition;
import ru.vsu.cs.sakovea.models.Content;
import ru.vsu.cs.sakovea.repository.ContentRepository;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;


    public ContentDto getContent(Integer id) {
        return ContentMapper.INSTANCE.toContentDto(contentRepository.findById(id));
    }

    public Content createContent(ContentDto contentDto) {
        Content content = new Content();

        if (contentDto != null){
            content.setContent(contentDto.getContent());
            content.setCompetition(CompetitionMapper.INSTANCE.toCompetition(contentDto.getCompetition()));
            content.setRefPage(RefValueMapper.INSTANCE.toRefValue(contentDto.getRefPage()));
            content.setRefFormat(RefValueMapper.INSTANCE.toRefValue(contentDto.getRefFormat()));
            content.setRefLanguage(RefValueMapper.INSTANCE.toRefValue(contentDto.getRefLanguage()));
        }
        return contentRepository.save(content);
    }
}
