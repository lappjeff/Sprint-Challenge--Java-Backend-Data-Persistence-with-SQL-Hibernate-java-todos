package lambda.sprintdatapersistencejava.service;

import lambda.sprintdatapersistencejava.model.ToDo;
import lambda.sprintdatapersistencejava.model.User;

import java.util.List;

public interface TodoService
{
	List<ToDo> findAll();

	ToDo findTodoById(long id);

	ToDo save(ToDo todo	);

	ToDo update(ToDo todo, long id);
}
