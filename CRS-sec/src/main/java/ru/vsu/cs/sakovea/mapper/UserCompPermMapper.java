package ru.vsu.cs.sakovea.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.sakovea.api.dto.userCompPerms.GetUserCompPermsDto;
import ru.vsu.cs.sakovea.api.dto.userCompPerms.UserCompPermDto;
import ru.vsu.cs.sakovea.models.UserCompPerm;

import java.util.List;
import java.util.Optional;


@Mapper
public interface UserCompPermMapper {

    UserCompPermMapper INSTANCE = Mappers.getMapper(UserCompPermMapper.class);

    UserCompPermDto toUserCompPermDto(UserCompPerm userCompPerm);

    GetUserCompPermsDto toGetUserCompPermsDtoDto(UserCompPerm userCompPerm);

    @IterableMapping(elementTargetType = UserCompPermDto.class)
    List<GetUserCompPermsDto> toGetUserCompPermsDtoList(List<UserCompPerm> userCompPermList);

    UserCompPermDto toUserCompPermDto(Optional<UserCompPerm> userCompPerm);

    @IterableMapping(elementTargetType = UserCompPermDto.class)
    List<UserCompPermDto> toUserCompPermDtoList(List<UserCompPerm> userCompPermList);

    UserCompPerm toUserCompPerm(UserCompPermDto contentDto);

    @IterableMapping(elementTargetType = UserCompPerm.class)
    List<UserCompPerm> toUserCompPermList(List<UserCompPermDto> userCompPermDtoList);
}
