package javatodos.repo;

import javatodos.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface RoleRepository extends CrudRepository<Role, Long>
{
}
