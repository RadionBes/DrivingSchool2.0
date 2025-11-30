package radion.ru.userservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import radion.ru.userservice.dto.SignUpUserDto;
import radion.ru.userservice.dto.UserResponse;
import radion.ru.userservice.dto.UserUpdate;
import radion.ru.userservice.entity.UserSchool;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserSchool mapToUser(SignUpUserDto signUpUserDto);
    UserResponse mapToUserResponse(UserSchool userSchool);
    UserSchool mapToUser(UserUpdate update);
}
