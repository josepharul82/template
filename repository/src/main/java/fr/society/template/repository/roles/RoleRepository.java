package fr.society.template.repository.roles;

import fr.society.template.model.Role;
import fr.society.template.repository.IRepository;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryCustom, IRepository {
}
