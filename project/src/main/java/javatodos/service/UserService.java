package javatodos.service;


import javatodos.models.ToDo;
import javatodos.models.User;

import java.util.List;

public interface UserService
{
	List<User> findAll();

	User findUserById(long id);

	User findUserByUsername(String username);

	void addTodoToUser(ToDo todo, long userid);

	void delete(long id);

	User save(User user);

	User update(User user, long id);
}
