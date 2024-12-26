package ru.vsu.cs.sakovea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.competition.EventDto;
import ru.vsu.cs.sakovea.api.dto.competition.GetCompetitionDto;
import ru.vsu.cs.sakovea.mapper.*;
import ru.vsu.cs.sakovea.models.Competition;
import ru.vsu.cs.sakovea.repository.CompetitionRepository;
import ru.vsu.cs.sakovea.repository.RefValueRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    private final RefValueRepository refValueRepository;


    public Competition createCompetition(CompetitionDto competitionDto) {
        Competition competition = new Competition();

        if (competitionDto.getParent() != null){
            if (competitionDto.getRefCompCount().getValueCid()
                    .equals(refValueRepository.findRefValueByValueCid("Individual").getValueCid())){
                return competitionRepository.save(createSingleCompetition(competitionDto));
            }
            return competitionRepository.save(createTeamCompetition(competitionDto));
        } else {
            competition.setName(competitionDto.getName());
            competition.setStartDate(competitionDto.getStartDate());
            competition.setEndDate(competitionDto.getEndDate());
            competition.setCid(competitionDto.getCid());
        }
        return competitionRepository.save(competition);
    }

    private Competition createTeamCompetition(CompetitionDto competitionDto) {
        Competition competition = new Competition();
        competition.setName(competitionDto.getName());
        competition.setMinNumOfTeamMem(competitionDto.getMinNumOfTeamMem());
        competition.setMaxNumOfTeamMem(competitionDto.getMaxNumOfTeamMem());
        return createAllCompetition(competitionDto, competition);
    }

    private Competition createSingleCompetition(CompetitionDto competitionDto) {
        Competition competition = new Competition();
        competition.setName(competitionDto.getName());
        return createAllCompetition(competitionDto, competition);
    }

    private Competition createAllCompetition(CompetitionDto competitionDto, Competition competition) {
        competition.setParent(CompetitionMapper.INSTANCE.toCompetition(competitionDto.getParent()));
        competition.setStartDate(competitionDto.getStartDate());
        competition.setEndDate(competitionDto.getEndDate());
        competition.setCid(competitionDto.getCid());
        competition.setParent(competitionRepository.findByParentId(competitionDto.getParent().getId()));
        competition.setRefComp(refValueRepository.findRefValueByValueCid(competitionDto.getRefComp().getValueCid()));
        competition.setRefCompCount(refValueRepository.findRefValueByValueCid(competitionDto.getRefCompCount().getValueCid()));
        competition.setRefCompAge(refValueRepository.findRefValueByValueCid(competitionDto.getRefCompAge().getValueCid()));
        return competition;
    }

    public Competition updateCompetition(CompetitionDto competitionDto) {
        Competition competition = competitionRepository.findById(competitionDto.getId());

        if (competition.getParent() != null){
            if (competition.getRefCompCount().getValueCid()
                    .equals(refValueRepository.findRefValueByValueCid("Individual").getValueCid())){
                return competitionRepository.save(updateSingleCompetition(competition, competitionDto));
            }
            return competitionRepository.save(updateTeamCompetition(competition, competitionDto));
        } else {
            if (competitionDto.getName() != null){
                competition.setName(competitionDto.getName());
            }
            if (competitionDto.getStartDate() != null){
                competition.setStartDate(competitionDto.getStartDate());
            }
            if (competitionDto.getEndDate() != null){
                competition.setEndDate(competitionDto.getEndDate());
            }
            if (competitionDto.getCid() != null){
                competition.setCid(competitionDto.getCid());
            }
        }
        return competitionRepository.save(competition);
    }

    private Competition updateTeamCompetition(Competition competition, CompetitionDto competitionDto) {
        if (competitionDto.getName() != null){
            competition.setName(competitionDto.getName());
        }
        if (competitionDto.getMinNumOfTeamMem() != 0){
            competition.setMinNumOfTeamMem(competitionDto.getMinNumOfTeamMem());
        }
        if (competitionDto.getMaxNumOfTeamMem() != 0){
            competition.setMaxNumOfTeamMem(competitionDto.getMaxNumOfTeamMem());
        }
        return updateAllCompetition(competitionDto, competition);
    }

    private Competition updateSingleCompetition(Competition competition, CompetitionDto competitionDto) {
        if (competitionDto.getName() != null){
            competition.setName(competitionDto.getName());
        }
        return updateAllCompetition(competitionDto, competition);
    }

    private Competition updateAllCompetition(CompetitionDto competitionDto, Competition competition) {
        if (competitionDto.getStartDate() != null){
            competition.setStartDate(competitionDto.getStartDate());
        }
        if (competitionDto.getEndDate() != null){
            competition.setEndDate(competitionDto.getEndDate());
        }
        if (competitionDto.getParent().getId() != 0){
            competition.setParent(competitionRepository.findByParentId(competitionDto.getParent().getId()));
        }
        if (competitionDto.getCid() != null){
            competition.setCid(competitionDto.getCid());
        }
        if (competitionDto.getRefComp().getValueCid() != null){
            competition.setRefComp(refValueRepository.findRefValueByValueCid(competitionDto.getRefComp().getValueCid()));
        }
        if (competitionDto.getRefCompCount().getValueCid() != null){
            competition.setRefCompCount(refValueRepository.findRefValueByValueCid(competitionDto.getRefCompCount().getValueCid()));
        }
        if (competitionDto.getRefCompAge().getValueCid() != null){
            competition.setRefCompAge(refValueRepository.findRefValueByValueCid(competitionDto.getRefCompAge().getValueCid()));
        }
        if (competitionDto.getContents() != null){
            competition.setContents(ContentMapper.INSTANCE.toContentList(competitionDto.getContents()));
        }
        if (competitionDto.getFields() != null){
            competition.setFields(FieldsMapper.INSTANCE.toFieldList(competitionDto.getFields()));
        }
        if (competitionDto.getFiles() != null){
            competition.setFiles(FilesMapper.INSTANCE.toFileList(competitionDto.getFiles()));
        }
        return competition;
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
        GetCompetitionDto competitionDto = new GetCompetitionDto();
        Competition competition = competitionRepository.findById(id).get();
        competitionDto.setName(competition.getName());
        competitionDto.setStartDate(competition.getStartDate());
        competitionDto.setEndDate(competition.getEndDate());
        competitionDto.setCid(competition.getCid());
        competitionDto.setRefComp(RefValueMapper.INSTANCE.toRefValueDto(competition.getRefComp()));
        competitionDto.setRefCompCount(RefValueMapper.INSTANCE.toRefValueDto(competition.getRefCompCount()));
        competitionDto.setRefCompAge(RefValueMapper.INSTANCE.toRefValueDto(competition.getRefCompAge()));
        return competitionDto;
    }

    public List<GetCompetitionDto> getAllCompetition(Integer eventId) {
        return null;
    }
}
