package javatodos.todos.repo;

import javatodos.todos.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long>
{
}
