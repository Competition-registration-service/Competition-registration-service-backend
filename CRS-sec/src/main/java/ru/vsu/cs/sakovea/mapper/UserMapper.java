package ru.vsu.cs.sakovea.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;
import ru.vsu.cs.sakovea.api.dto.user.GetUserDto;
import ru.vsu.cs.sakovea.api.dto.user.GetUserForAdminDto;
import ru.vsu.cs.sakovea.api.dto.user.UserDto;
import ru.vsu.cs.sakovea.models.User;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    GetUserDto toGetUserDto(User user);

    @IterableMapping(elementTargetType = UserDto.class)
    List<UserDto> toUserDtoList(List<User> users);

    @IterableMapping(elementTargetType = UserDto.class)
    List<GetUserForAdminDto> toUserForAdminDtoList(List<User> users);

    UserDto toUserDto(User user);
}
