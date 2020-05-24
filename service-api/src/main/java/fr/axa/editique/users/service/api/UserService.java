package fr.axa.editique.users.service.api;


import fr.axa.editique.users.dto.SearchUserDTO;
import fr.axa.editique.users.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService extends IService{

    UserDTO findById(Long userId);
    Page<UserDTO> findByCriteria(SearchUserDTO searchUserDTO);
    List<UserDTO> findAll(Iterable<Long> userIds);
    UserDTO save(UserDTO userDTO);
    void delete(Long id);

}
