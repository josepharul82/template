package fr.society.template.repository.users;

import fr.society.template.dto.SearchUserDTO;
import fr.society.template.model.User;
import fr.society.template.repository.IRepository;
import org.springframework.data.domain.Page;

public interface UserRepositoryCustom extends IRepository {
    Page<User> findByCriteria(SearchUserDTO searchUserDTO);
}
