package javatodos.repo;

import javatodos.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends CrudRepository<User, Long>
{
	User findByUsername(String username);
}
