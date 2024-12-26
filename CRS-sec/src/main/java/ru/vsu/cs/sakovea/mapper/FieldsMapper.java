package ru.vsu.cs.sakovea.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.sakovea.api.dto.FieldDto;
import ru.vsu.cs.sakovea.models.Field;

import java.util.List;

@Mapper
public interface FieldsMapper {

    FieldsMapper INSTANCE = Mappers.getMapper(FieldsMapper.class);

    FieldDto toFieldDto(Field content);

    @IterableMapping(elementTargetType = FieldDto.class)
    List<FieldDto> toFieldDtoList(List<Field> fieldList);

    Field toField(FieldDto fieldDto);

    @IterableMapping(elementTargetType = Field.class)
    List<Field> toFieldList(List<FieldDto> fieldDtoList);
}
