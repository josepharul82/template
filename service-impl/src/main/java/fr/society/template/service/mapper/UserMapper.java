package fr.society.template.service.mapper;

import fr.society.template.dto.UserDTO;
import fr.society.template.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends IMapper<User,UserDTO> {
}
