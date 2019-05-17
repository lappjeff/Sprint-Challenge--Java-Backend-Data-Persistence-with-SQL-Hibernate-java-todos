package lambda.sprintdatapersistencejava.repository;

import lambda.sprintdatapersistencejava.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
	User findByUsername(String name);

	@Modifying
	@Query(value = "DELETE FROM userroles WHERE userid = :userid", nativeQuery = true)
	void deleteUserRole(long userid);
}