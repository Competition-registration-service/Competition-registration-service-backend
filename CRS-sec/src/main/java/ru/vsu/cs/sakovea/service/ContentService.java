package ru.vsu.cs.sakovea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.exeptions.ForbiddenException;
import ru.vsu.cs.sakovea.mapper.CompetitionMapper;
import ru.vsu.cs.sakovea.mapper.ContentMapper;
import ru.vsu.cs.sakovea.mapper.RefValueMapper;
import ru.vsu.cs.sakovea.models.Content;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;
import ru.vsu.cs.sakovea.repository.ContentRepository;
import ru.vsu.cs.sakovea.repository.RefValueRepository;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    private final RefValueRepository refValueRepository;

    private void forbidAccessForNullUserRole(UserDetailsImpl userDetails) {
        if (Boolean.TRUE.equals(userDetails.getUser().isAdmin())) {
            return;
        }
        if (userDetails.getUserCompPerm() == null ||
                (!userDetails.getUserCompPerm().getRefRole()
                        .getValueCid().equals(refValueRepository.findRefValueByValueCid("ADMIN").getValueCid()))) {
            throw new ForbiddenException("Доступ запрещён");
        }
    }


    public ContentDto getContent(Integer id) {
        return ContentMapper.INSTANCE.toContentDto(contentRepository.findById(id));
    }

    public Content createContent(UserDetailsImpl userDetails, ContentDto contentDto) {
        forbidAccessForNullUserRole(userDetails);
        Content content = new Content();

        if (contentDto != null){
            content.setFeelingContent(contentDto.getFeelingContent());
            content.setCompetition(CompetitionMapper.INSTANCE.toCompetition(contentDto.getCompetition()));
            content.setRefPage(RefValueMapper.INSTANCE.toRefValue(contentDto.getRefPage()));
            content.setRefFormat(RefValueMapper.INSTANCE.toRefValue(contentDto.getRefFormat()));
            content.setRefLanguage(RefValueMapper.INSTANCE.toRefValue(contentDto.getRefLanguage()));
        }
        return contentRepository.save(content);
    }

    public Content updateContent(UserDetailsImpl userDetails, ContentDto contentDto) {
        forbidAccessForNullUserRole(userDetails);
        Content content = new Content();

        if (contentDto.getFeelingContent() != null){
            content.setFeelingContent(contentDto.getFeelingContent());
        }
        if (contentDto.getCompetition() != null){
            content.setCompetition(CompetitionMapper.INSTANCE.toCompetition(contentDto.getCompetition()));

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
}
