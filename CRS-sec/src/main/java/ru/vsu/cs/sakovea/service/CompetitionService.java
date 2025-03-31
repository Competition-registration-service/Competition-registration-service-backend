package ru.vsu.cs.sakovea.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sakovea.api.dto.competition.*;
import ru.vsu.cs.sakovea.api.dto.field.CreateFieldDto;
import ru.vsu.cs.sakovea.api.dto.field.ResponseFieldDto;
import ru.vsu.cs.sakovea.api.dto.fieldvalue.FieldValueDto;
import ru.vsu.cs.sakovea.api.dto.fieldvalue.RequestFieldValueDto;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueResponseDto;
import ru.vsu.cs.sakovea.exeptions.CustomException;
import ru.vsu.cs.sakovea.mapper.*;
import ru.vsu.cs.sakovea.models.*;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import ru.vsu.cs.sakovea.models.enums.Role;
import ru.vsu.cs.sakovea.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    private final RefValueRepository refValueRepository;

    private final FieldRepository fieldRepository;

    private final ContestantRepository contestantRepository;

    private final FieldValueRepository fieldValueRepository;

    private final TeamRepository teamRepository;

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


    public EventDto getEvent(Integer id, UserDetailsImpl userDetails) {
        EventDto eventDto = new EventDto();
        Competition event = competitionRepository.findById(id).orElse(null);
        if (event != null) {
            eventDto.setName(event.getName());
            eventDto.setStartDate(event.getStartDate());
            eventDto.setEndDate(event.getEndDate());
            eventDto.setCid(event.getCid());
            eventDto.setCompetitions(CompetitionMapper.INSTANCE.toGetCompetitionDtoList(event.getCompetitions()));
            eventDto.setContents(ContentMapper.INSTANCE.toResponseContentDtoList(event.getContents()));
            eventDto.setRole(RefValueMapper.INSTANCE.toRefValueDto(userDetails.getUser().getRoles().getFirst().getRefRole()));
            return eventDto;
        }
        throw new CustomException("Такого мероприятия не существует!");
    }

    public GetCompetitionDto getCompetition(Integer eventId, Integer id, UserDetailsImpl userDetails) {
        Competition competition = competitionRepository.findByIdAndParent(id, competitionRepository.findById(eventId).get());
        GetCompetitionDto getCompetitionDto = new GetCompetitionDto();
        if (competition != null) {
            getCompetitionDto.setName(competition.getName());
            getCompetitionDto.setStartDate(competition.getStartDate());
            getCompetitionDto.setEndDate(competition.getEndDate());
            getCompetitionDto.setCid(competition.getCid());
            getCompetitionDto.setMinNumOfTeamMem(competition.getMinNumOfTeamMem());
            getCompetitionDto.setMaxNumOfTeamMem(competition.getMaxNumOfTeamMem());
            getCompetitionDto.setRefComp(RefValueMapper.INSTANCE.toRefValueDto(competition.getRefComp()));
            getCompetitionDto.setRefCompCount(RefValueMapper.INSTANCE.toRefValueDto(competition.getRefCompCount()));
            getCompetitionDto.setRefCompAge(RefValueMapper.INSTANCE.toRefValueDto(competition.getRefCompAge()));
            getCompetitionDto.setContents(ContentMapper.INSTANCE.toContentDtoList(competition.getContents()));
            getCompetitionDto.setContestants(ContestantMapper.INSTANCE.toGetContestantDtoList(competition.getContestants()));
            getCompetitionDto.setTeams(TeamMapper.INSTANCE.toGetTeamForCompetitionDtoList(competition.getTeams()));
            getCompetitionDto.setRole(RefValueMapper.INSTANCE.toRefValueDto(userDetails.getUser().getRoles().getFirst().getRefRole()));
            return CompetitionMapper.INSTANCE.toGetCompetitionDto(competition);
        }
        throw new CustomException("Такого соревнования не существует!");
    }

    public List<GetCompetitionDto> getAllCompetition(Integer eventId) {
        Competition event = competitionRepository.findById(eventId).get();
        return CompetitionMapper.INSTANCE.toGetCompetitionDtoList(event.getCompetitions());
    }


    public List<ResponseFieldDto> getCompetitionRegistrationPage(Integer id, Integer competitionId) {
        Competition event = competitionRepository.findById(id).orElse(null);
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
        Competition event = competitionRepository.findById(id).orElse(null);
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
        Competition event = competitionRepository.findById(id).orElse(null);
        if (event == null) {
            throw new CustomException("Такого мероприятия не существует!");
        } else {
            Competition competition = competitionRepository.findById(competitionId).get();

            if (event.getCompetitions().contains(competition)) {
                User user = userDetails.getUser();
                // todo продумать (сделать отдельную функцию регистрации участника и члена команды на соревнование)
                Contestant contestant = new Contestant();
                contestant.setUser(user);
                contestant.setTeamCreator(false);


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
                    fieldValue.setField(FieldsMapper.INSTANCE.toField(fieldValueDto.getField()));
                    fieldValueRepository.save(fieldValue);
                }
                UserCompPerm role = new UserCompPerm();
                role.setCompetition(competition);
                role.setUser(user);
                role.setRefRole(refValueRepository.findRefValueByValueCid(Role.CONTESTANT.toString()));
                contestant.getUser().getRoles().add(role);
                userCompPermsRepository.save(role);
                contestantRepository.save(contestant);
                return true;
                // todo сделать через юзкейсы с проверкой реф тайп. В реф тайп сделай еще записи по полям контестанта и команды.
                // из токена (юзер детайлс) брать юзера и совать в контестанта при его создании.
            }
            throw new CustomException("Соревнования не существует!");
        }
    }


    // todo Эффективным скуф запросом сделать в виде мапы а не листа (ключ=domain_cid, значение=List<Ref_value>)
    public Map<String, List<RefValueResponseDto>> getRefValues(UserDetailsImpl userDetails) {
        List<RefValue> refValues = refValueRepository.findAll();

        if (refValues.isEmpty()) {
            throw new CustomException("Словарь пустой!");
        }

        return refValues.stream()
                .collect(Collectors.groupingBy(
                        RefValue::getDomainCid,
                        Collectors.mapping(
                                RefValueMapper.INSTANCE::toRefValueResponseDto,
                                Collectors.toList()
                        )
                ));
    }

    public Object checkAccessCode(Integer id, UserDetailsImpl userDetails, Integer competitionId, String teamCode) {
        Competition event = competitionRepository.findById(id).orElse(null);
        if (event == null) {
            throw new CustomException("Такого мероприятия не существует!");
        } else {
            Competition competition = competitionRepository.findById(competitionId).get();
            if (event.getCompetitions().contains(competition)) {
                if (teamRepository.findTeamByAccessCode(teamCode) != null) {
                    Contestant contestant = new Contestant();
                    contestant.setUser(userDetails.getUser());
                    contestant.setTeam(teamRepository.findTeamByAccessCode(teamCode));
                    contestant.setCompetition(competition);
                    return "Команда найдена!";
                }
                throw new CustomException("Такой команды не существует! Неверный код!");
            }
            throw new CustomException("Соревнования не существует!");

        }
    }

    public Object capitanRegisterOnTeamCompetition(Integer id, Integer competitionId, UserDetailsImpl userDetails,
                                            List<RequestFieldValueDto> requestFieldValueDto) {
        Competition event = competitionRepository.findById(id).orElse(null);
        if (event == null) {
            throw new CustomException("Такого мероприятия не существует!");
        } else {
            Competition competition = competitionRepository.findById(competitionId).get();

            if (event.getCompetitions().contains(competition)) {
                User user = userDetails.getUser();
                Team team = new Team();
                Contestant contestant = new Contestant();
                contestant.setUser(user);
                contestant.setTeamCreator(true);
                team.setCompetition(competition);
                PasswordGenerator gen = new PasswordGenerator();
                String code = gen.generatePassword(10,
                        new CharacterRule(EnglishCharacterData.UpperCase, 5),
                        new CharacterRule(EnglishCharacterData.Digit, 5));
                team.setAccessCode(code);


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
                        case ("team_name"):
                            team.setName(fieldValueDto.getValue());
                            break;
                        default:
                            break;
                    }
                    FieldValue fieldValue = new FieldValue();
                    fieldValue.setValue(fieldValueDto.getValue());
                    fieldValue.setContestant(contestant);

                    fieldValue.setField(FieldsMapper.INSTANCE.toField(fieldValueDto.getField()));
                    fieldValueRepository.save(fieldValue);
                }
                UserCompPerm role = new UserCompPerm();
                role.setCompetition(competition);
                role.setUser(user);
                role.setRefRole(refValueRepository.findRefValueByValueCid(Role.CAPITAN.toString()));
                contestant.getUser().getRoles().add(role);
                userCompPermsRepository.save(role);
                teamRepository.save(team);
                contestant.setTeam(team);
                contestantRepository.save(contestant);
                return TeamMapper.INSTANCE.toGetTeamDto(team);
                // todo сделать через юзкейсы с проверкой реф тайп. В реф тайп сделай еще записи по полям контестанта и команды.
                // из токена (юзер детайлс) брать юзера и совать в контестанта при его создании.
            }
            throw new CustomException("Соревнования не существует!");
        }
    }

    public Object registerOnTeamCompetition(Integer id, Integer competitionId, UserDetailsImpl userDetails,
                                            List<RequestFieldValueDto> requestFieldValueDto) {
        Competition event = competitionRepository.findById(id).orElse(null);
        if (event == null) {
            throw new CustomException("Такого мероприятия не существует!");
        } else {
            Competition competition = competitionRepository.findById(competitionId).get();

            if (event.getCompetitions().contains(competition)) {
                User user = userDetails.getUser();
                Contestant contestant = contestantRepository.findByUserAndCompetition(user, competition);
                contestant.setTeamCreator(false);

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
                    fieldValue.setField(FieldsMapper.INSTANCE.toField(fieldValueDto.getField()));
                    fieldValueRepository.save(fieldValue);
                }
                UserCompPerm role = new UserCompPerm();
                role.setCompetition(competition);
                role.setUser(user);
                role.setRefRole(refValueRepository.findRefValueByValueCid(Role.CONTESTANT.toString()));
                contestant.getUser().getRoles().add(role);
                userCompPermsRepository.save(role);
                contestantRepository.save(contestant);
                return TeamMapper.INSTANCE.toGetTeamDto(contestant.getTeam());
            }
            throw new CustomException("Соревнования не существует!");
        }
    }
}







