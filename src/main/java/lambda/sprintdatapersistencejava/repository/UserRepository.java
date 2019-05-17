package lambda.sprintdatapersistencejava.repository;

import lambda.sprintdatapersistencejava.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
	User findByUsername(String name);
}