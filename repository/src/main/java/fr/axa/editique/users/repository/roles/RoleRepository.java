package fr.axa.editique.users.repository.roles;

import fr.axa.editique.users.model.Role;
import fr.axa.editique.users.repository.IRepository;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryCustom, IRepository {
}
