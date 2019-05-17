package lambda.sprintdatapersistencejava.service;

import lambda.sprintdatapersistencejava.model.ToDo;
import lambda.sprintdatapersistencejava.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService
{
	List<User> findAll();

	User findUserById(long id);

	User findUserByUsername(String username);

	void addTodoToUser(ToDo todo, String username);

	void delete(long id);

	User save(User user);

	User update(User user, long id);
}
