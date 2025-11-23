package radion.ru.userservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import radion.ru.userservice.dto.SignUpUserDto;
import radion.ru.userservice.entity.UserSchool;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserSchool mapToUser(SignUpUserDto signUpUserDto);
}
