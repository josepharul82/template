package fr.axa.editique.users.repository.users;

import fr.axa.editique.users.model.User;
import fr.axa.editique.users.repository.IRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom, IRepository {
}
