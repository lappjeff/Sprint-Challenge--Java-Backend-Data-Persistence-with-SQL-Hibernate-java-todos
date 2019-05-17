package lambda.sprintdatapersistencejava.service;

import lambda.sprintdatapersistencejava.model.ToDo;
import lambda.sprintdatapersistencejava.model.User;

import java.util.List;

public interface TodoService
{
	List<ToDo> findAll();

	User findUserById(long id);

	void delete(long id);

	User save(ToDo todo	);

	User update(ToDo user, long id);
}
