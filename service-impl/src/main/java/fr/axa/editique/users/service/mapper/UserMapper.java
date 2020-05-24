package fr.axa.editique.users.service.mapper;

import fr.axa.editique.users.dto.UserDTO;
import fr.axa.editique.users.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends IMapper<User,UserDTO> {
}
