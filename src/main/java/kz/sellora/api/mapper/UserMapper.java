package kz.sellora.api.mapper;

import kz.sellora.api.model.UserDTO;
import kz.sellora.core.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User entity);
}
