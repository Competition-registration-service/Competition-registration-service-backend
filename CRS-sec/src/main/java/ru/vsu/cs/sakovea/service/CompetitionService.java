package ru.vsu.cs.sakovea.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sakovea.api.dto.competition.*;
import ru.vsu.cs.sakovea.exeptions.ThrowMyException;
import ru.vsu.cs.sakovea.mapper.*;
import ru.vsu.cs.sakovea.models.Competition;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;
import ru.vsu.cs.sakovea.repository.CompetitionRepository;
import ru.vsu.cs.sakovea.repository.RefValueRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    private final RefValueRepository refValueRepository;

//    private void forbidAccessForNullUserRole(UserDetailsImpl userDetails) {
//        if (Boolean.TRUE.equals(userDetails.getUser().isAdmin())) {
//            return;
//        }
//        if (userDetails.getUserCompPerm() == null ||
//                (!userDetails.getUserCompPerm().getRefRole()
//                        .getValueCid().equals(refValueRepository.findRefValueByValueCid("ADMIN").getValueCid()))) {
//            throw new ThrowMyException("Доступ запрещён");
//        }
//    }

    private void checkIsUserAdmin(UserDetailsImpl userDetails) {
        if (Boolean.TRUE.equals(userDetails.getUser().isAdmin()) || userDetails.getUserCompPerm().getRefRole()
                .getValueCid().equals(refValueRepository.findRefValueByValueCid("ADMIN").getValueCid())) {
            return;
        }
        throw new ThrowMyException("Доступ запрещён");
    }

    public Competition createEvent(UserDetailsImpl userDetails, CreateEventDto eventDto) {
        checkIsUserAdmin(userDetails);
        Competition competition = new Competition();

        if (eventDto != null) {
            competition.setName(eventDto.getName());
            competition.setStartDate(eventDto.getStartDate());
            competition.setEndDate(eventDto.getEndDate());
            competition.setCid(eventDto.getCid());
            return competitionRepository.save(competition);
        }
        throw new ThrowMyException("Данные отсутствуют");
    }


    public Competition updateEvent(UserDetailsImpl userDetails, CompetitionDto competitionDto) {
        checkIsUserAdmin(userDetails);
        Competition competition = competitionRepository.findById(competitionDto.getId());

        if (competitionDto.getName() != null) {
            competition.setName(competitionDto.getName());
        }
        if (competitionDto.getStartDate() != null) {
            competition.setStartDate(competitionDto.getStartDate());
        }
        if (competitionDto.getEndDate() != null) {
            competition.setEndDate(competitionDto.getEndDate());
        }
        if (competitionDto.getCid() != null) {
            competition.setCid(competitionDto.getCid());
        }

        return competitionRepository.save(competition);
    }


    @Transactional
    public Competition createCompetition(UserDetailsImpl userDetails, CompetitionCreateDto competitionDto, int eventId) {
        checkIsUserAdmin(userDetails);
        Competition competition = new Competition();
        if (competitionDto != null) {
            competition.setName(competitionDto.getName());
            competition.setStartDate(competitionDto.getStartDate());
            competition.setEndDate(competitionDto.getEndDate());
            competition.setCid(competitionDto.getCid());
            competition.setRefComp(RefValueMapper.INSTANCE.toRefValue(competitionDto.getRefComp()));
            competition.setRefCompCount(RefValueMapper.INSTANCE.toRefValue(competitionDto.getRefCompCount()));
            competition.setRefCompAge(RefValueMapper.INSTANCE.toRefValue(competitionDto.getRefCompAge()));
            competition.setCompetitionContent(competitionDto.getCompetitionContent());
            if (competitionDto.getMaxNumOfTeamMem() != null && competitionDto.getMinNumOfTeamMem() != null
                    || competitionDto.getMaxNumOfTeamMem() == null && competitionDto.getMinNumOfTeamMem() != null ||
                    competitionDto.getMaxNumOfTeamMem() != null && competitionDto.getMinNumOfTeamMem() == null) {
                competition.setMaxNumOfTeamMem(competitionDto.getMaxNumOfTeamMem());
                competition.setMinNumOfTeamMem(competitionDto.getMinNumOfTeamMem());
            }
            competitionRepository.save(competition);
            Competition event = competitionRepository.findById(eventId);
            if (event != null) {
                competition.setParent(event);
                competitionRepository.save(competition);
                event.getCompetitions().add(competition);
                competitionRepository.save(event);
            } else throw new ThrowMyException("Мероприятия с таким ID не существует!");
            return competition;
        }
        throw new ThrowMyException("Данные отсутствуют");
    }


    public Competition updateCompetition(UserDetailsImpl userDetails, CompetitionDto competitionDto) {
        checkIsUserAdmin(userDetails);
        Competition competition = competitionRepository.findById(competitionDto.getId());

        if (competitionDto.getName() != null) {
            competition.setName(competitionDto.getName());
        }
        if (competitionDto.getStartDate() != null) {
            competition.setStartDate(competitionDto.getStartDate());
        }
        if (competitionDto.getEndDate() != null) {
            competition.setEndDate(competitionDto.getEndDate());
        }
        if (competitionDto.getCid() != null) {
            competition.setCid(competitionDto.getCid());
        }
        if (competitionDto.getRefComp() != null) {
            competition.setRefComp(RefValueMapper.INSTANCE.toRefValue(competitionDto.getRefComp()));
        }
        if (competitionDto.getRefCompCount() != null) {
            competition.setRefCompCount(RefValueMapper.INSTANCE.toRefValue(competitionDto.getRefCompCount()));
        }
        if (competitionDto.getRefCompAge() != null) {
            competition.setRefCompAge(RefValueMapper.INSTANCE.toRefValue(competitionDto.getRefCompAge()));
        }
        if (competitionDto.getCompetitionContent() != null) {
            competition.setCompetitionContent(competitionDto.getCompetitionContent());
        }
        if (competitionDto.getMinNumOfTeamMem() != -1){
            competition.setMinNumOfTeamMem(competitionDto.getMinNumOfTeamMem());
        }
        if (competitionDto.getMaxNumOfTeamMem() != -1){
            competition.setMaxNumOfTeamMem(competitionDto.getMaxNumOfTeamMem());
        }

            return competitionRepository.save(competition);
    }


    public EventDto getEvent(Integer id) {
        EventDto eventDto = new EventDto();
        Competition event = competitionRepository.findById(id).get();
        eventDto.setName(event.getName());
        eventDto.setStartDate(event.getStartDate());
        eventDto.setEndDate(event.getEndDate());
        eventDto.setCid(event.getCid());
        eventDto.setCompetitions(CompetitionMapper.INSTANCE.toCompetitionDtoList(event.getCompetitions()));
        eventDto.setContents(ContentMapper.INSTANCE.toContentDtoList(event.getContents()));
        return eventDto;
    }

    public GetCompetitionDto getCompetition(Integer id) {
        return CompetitionMapper.INSTANCE.toGetCompetitionDto(competitionRepository.findById(id).get());
    }

    public List<GetCompetitionDto> getAllCompetition(Integer eventId) {
        Competition event = competitionRepository.findById(eventId).get();
        return CompetitionMapper.INSTANCE.toGetCompetitionDtoList(event.getCompetitions());
    }


}





