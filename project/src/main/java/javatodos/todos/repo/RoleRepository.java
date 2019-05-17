package javatodos.todos.repo;

import javatodos.todos.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface RoleRepository extends CrudRepository<Role, Long>
{
}
