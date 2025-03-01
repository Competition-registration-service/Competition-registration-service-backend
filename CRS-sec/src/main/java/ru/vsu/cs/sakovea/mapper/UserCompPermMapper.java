package ru.vsu.cs.sakovea.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.sakovea.api.dto.user.UserDto;
import ru.vsu.cs.sakovea.api.dto.userCompPerms.UserCompPermDto;
import ru.vsu.cs.sakovea.models.User;
import ru.vsu.cs.sakovea.models.UserCompPerm;

import java.util.List;

public interface UserCompPermMapper {

    UserCompPermMapper INSTANCE = Mappers.getMapper(UserCompPermMapper.class);

    UserCompPermDto toUserCompPermDto(UserCompPerm userCompPerm);


    @IterableMapping(elementTargetType = UserCompPermDto.class)
    List<UserCompPermDto> toUserCompPermDtoList(List<UserCompPerm> users);
}
