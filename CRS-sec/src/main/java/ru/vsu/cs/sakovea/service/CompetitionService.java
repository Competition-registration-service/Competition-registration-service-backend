package ru.vsu.cs.sakovea.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sakovea.api.dto.competition.*;
import ru.vsu.cs.sakovea.api.dto.field.CreateFieldDto;
import ru.vsu.cs.sakovea.api.dto.field.ResponseFieldDto;
import ru.vsu.cs.sakovea.api.dto.fieldvalue.FieldValueDto;
import ru.vsu.cs.sakovea.api.dto.fieldvalue.RequestFieldValueDto;
import ru.vsu.cs.sakovea.exeptions.CustomException;
import ru.vsu.cs.sakovea.mapper.*;
import ru.vsu.cs.sakovea.models.*;
import ru.vsu.cs.sakovea.models.enums.Role;
import ru.vsu.cs.sakovea.repository.CompetitionRepository;
import ru.vsu.cs.sakovea.repository.FieldRepository;
import ru.vsu.cs.sakovea.repository.RefValueRepository;
import ru.vsu.cs.sakovea.repository.UserCompPermsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    private final RefValueRepository refValueRepository;

    private final FieldRepository fieldRepository;

    private Integer MAX_NUM_OF_TEAM_MEM = 5;
    private Integer MIN_NUM_OF_TEAM_MEM = 1;
    private final UserCompPermsRepository userCompPermsRepository;

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
        if (Boolean.TRUE.equals(userDetails.getUser().isAdmin()) || (userDetails.getUser().getRoles().getFirst().
                getRefRole().getValueCid().equals(refValueRepository.findRefValueByValueCid("ADMIN").getValueCid()))) {
            return;
        }
        throw new CustomException("Доступ запрещён");
    }

    public Competition createEvent(UserDetailsImpl userDetails, CreateEventDto eventDto) {
        Competition competition = new Competition();
        if (eventDto != null) {
            checkIsUserAdmin(userDetails);
            System.out.println(eventDto.getId());
            competition.setName(eventDto.getName());
            competition.setStartDate(eventDto.getStartDate());
            competition.setEndDate(eventDto.getEndDate());
            competition.setCid(eventDto.getCid());
            return competitionRepository.save(competition);
        }
        throw new CustomException("Данные отсутствуют");
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
            Competition event = competitionRepository.findById(eventId);
            if (event != null) {
                competition.setName(competitionDto.getName());
                competition.setStartDate(competitionDto.getStartDate());
                competition.setEndDate(competitionDto.getEndDate());
                competition.setCid(competitionDto.getCid());
                competition.setRefComp(RefValueMapper.INSTANCE.toRefValue(competitionDto.getRefComp()));
                competition.setRefCompCount(RefValueMapper.INSTANCE.toRefValue(competitionDto.getRefCompCount()));
                competition.setRefCompAge(RefValueMapper.INSTANCE.toRefValue(competitionDto.getRefCompAge()));
                competition.setCompetitionContent(competitionDto.getCompetitionContent());
                if (competitionDto.getMaxNumOfTeamMem() != null && competitionDto.getMinNumOfTeamMem() != null) {
                    competition.setMaxNumOfTeamMem(competitionDto.getMaxNumOfTeamMem());
                    competition.setMinNumOfTeamMem(competitionDto.getMinNumOfTeamMem());
                } else if (competitionDto.getMaxNumOfTeamMem() == null && competitionDto.getMinNumOfTeamMem() != null) {
                    competition.setMaxNumOfTeamMem(MAX_NUM_OF_TEAM_MEM);
                    competition.setMinNumOfTeamMem(competitionDto.getMinNumOfTeamMem());
                } else if (competitionDto.getMaxNumOfTeamMem() != null && competitionDto.getMinNumOfTeamMem() == null) {
                    competition.setMaxNumOfTeamMem(competitionDto.getMaxNumOfTeamMem());
                    competition.setMinNumOfTeamMem(MIN_NUM_OF_TEAM_MEM);
                }
                competition.setParent(event);
                competitionRepository.save(competition);
                event.getCompetitions().add(competition);
                competitionRepository.save(event);
                return competition;
            } else throw new CustomException("Мероприятия с таким ID не существует! Создать соревнование" +
                    " в несуществующем мероприятии невозможно");
        }
        throw new CustomException("Данные отсутствуют");
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
        if (competitionDto.getMinNumOfTeamMem() != -1) {
            competition.setMinNumOfTeamMem(competitionDto.getMinNumOfTeamMem());
        }
        if (competitionDto.getMaxNumOfTeamMem() != -1) {
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
        eventDto.setCompetitions(CompetitionMapper.INSTANCE.toGetCompetitionDtoList(event.getCompetitions()));
        eventDto.setContents(ContentMapper.INSTANCE.toResponseContentDtoList(event.getContents()));
        return eventDto;
    }

    public GetCompetitionDto getCompetition(Integer eventId, Integer id) {
        Competition competition = competitionRepository.findByIdAndParent(id, competitionRepository.findById(eventId).get());
        if (competition != null) {
            System.out.println(CompetitionMapper.INSTANCE.toGetCompetitionDto(competition));
            return CompetitionMapper.INSTANCE.toGetCompetitionDto(competition);
        }
        throw new CustomException("Тело пришло пустое из БД");
    }

    public List<GetCompetitionDto> getAllCompetition(Integer eventId) {
        Competition event = competitionRepository.findById(eventId).get();
        return CompetitionMapper.INSTANCE.toGetCompetitionDtoList(event.getCompetitions());
    }


    public List<ResponseFieldDto> getCompetitionRegistrationPage(Integer id, Integer competitionId) {
        Competition event = competitionRepository.findById(id).get();
        if (event == null) {
            throw new CustomException("Такого мероприятия не существует!");
        } else {
            Competition competition = competitionRepository.findById(competitionId).get();

            if (event.getCompetitions().contains(competition)) {
                return FieldsMapper.INSTANCE.toResponseFieldDtoList(competition.getFields());
            }
            throw new CustomException("Соревнования не существует!");
        }
    }

    public Object createCompetitionRegistrationPage(UserDetailsImpl userDetails, CreateFieldDto createFieldDto,
                                                    Integer id, Integer competitionId) {
        checkIsUserAdmin(userDetails);
        Competition event = competitionRepository.findById(id).get();
        if (event == null) {
            throw new CustomException("Такого мероприятия не существует!");
        } else {
            Competition competition = competitionRepository.findById(competitionId).get();

            if (event.getCompetitions().contains(competition)) {
                Field field = new Field();
                field.setSelectDomainCid(createFieldDto.getSelectDomainCid());
                field.setTeamField(createFieldDto.isTeamField());
                field.setCid(createFieldDto.getCid());
                field.setShortName(createFieldDto.getShortName());
                field.setLongName(createFieldDto.getLongName());
                field.setComment(createFieldDto.getComment());
                field.setExampleValue(createFieldDto.getExampleValue());
                field.setMaxLength(createFieldDto.getMaxLength());
                field.setOrderr(createFieldDto.getOrderr());
                field.setOptional(createFieldDto.isOptional());
                field.setRefType(RefValueMapper.INSTANCE.toRefValue(createFieldDto.getRefType()));
                competition.getFields().add(field);
                field.setCompetition(competition);
                fieldRepository.save(field);
            }
            throw new CustomException("Соревнования не существует!");
        }
    }

    public Object registerOnCompetition(Integer id, Integer competitionId, UserDetailsImpl userDetails,
                                        List<RequestFieldValueDto> requestFieldValueDto) {
        Competition event = competitionRepository.findById(id).get();
        if (event == null) {
            throw new CustomException("Такого мероприятия не существует!");
        } else {
            Competition competition = competitionRepository.findById(competitionId).get();

            if (event.getCompetitions().contains(competition)) {
                User user = userDetails.getUser();
                Contestant contestant = new Contestant();
                contestant.setUser(user);


                for (RequestFieldValueDto fieldValueDto : requestFieldValueDto) {
                    String type = fieldValueDto.getField().getRefType().getValueCid();
                    switch (type) {
                        case ("surname"):
                            contestant.setSurname(fieldValueDto.getValue());
                            break;
                        case ("name"):
                            contestant.setName(fieldValueDto.getValue());
                            break;
                        case ("patronymic"):
                            contestant.setThirdname(fieldValueDto.getValue());
                            break;
                        case ("login"):
                            contestant.setNickname(fieldValueDto.getValue());
                            break;
                        case ("phone"):
                            contestant.setPhone(fieldValueDto.getValue());
                            break;
                        case ("email"):
                            contestant.setEmail(fieldValueDto.getValue());
                            break;
                        case ("vk"):
                            contestant.setVk(fieldValueDto.getValue());
                            break;
                        case ("telegram"):
                            contestant.setTelegram(fieldValueDto.getValue());
                            break;
                        default:
                            break;
                    }
                    FieldValue fieldValue = new FieldValue();
                    fieldValue.setValue(fieldValueDto.getValue());
                    fieldValue.setContestant(contestant);
                    if (competition.getRefCompCount().getValueCid().equals("Team")) {
                        fieldValue.setTeam(contestant.getTeam());
                    }
                    fieldValue.setField(FieldsMapper.INSTANCE.toField(fieldValueDto.getField()));
                }
                List<FieldValueDto> fieldValueDtos = new ArrayList<>();
                // todo сделать через юзкейсы с проверкой реф тайп. В реф тайп сделай еще записи по полям контестанта и команды.
                // из токена (юзер детайлс) брать юзера и совать в контестанта при его создании.
            }
            throw new CustomException("Соревнования не существует!");
        }
    }

    public Object getRefValues(UserDetailsImpl userDetails) {
        checkIsUserAdmin(userDetails);
        List<RefValue> refValues = refValueRepository.findAll();
        if (refValues == null) {
            throw new CustomException("Словарь пустой!");
        }
        return RefValueMapper.INSTANCE.toRefValueResponseDtoList(refValues);
    }
}





