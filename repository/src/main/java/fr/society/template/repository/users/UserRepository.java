package fr.society.template.repository.users;

import fr.society.template.model.User;
import fr.society.template.repository.IRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom, IRepository {
}
