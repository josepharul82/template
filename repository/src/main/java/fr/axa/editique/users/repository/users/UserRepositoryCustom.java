package fr.axa.editique.users.repository.users;

import fr.axa.editique.users.dto.SearchUserDTO;
import fr.axa.editique.users.model.User;
import fr.axa.editique.users.repository.IRepository;
import org.springframework.data.domain.Page;

public interface UserRepositoryCustom extends IRepository {
    Page<User> findByCriteria(SearchUserDTO searchUserDTO);
}
