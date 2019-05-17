package lambda.sprintdatapersistencejava.service;

import lambda.sprintdatapersistencejava.model.ToDo;
import lambda.sprintdatapersistencejava.model.User;
import lambda.sprintdatapersistencejava.repository.TodoRepository;
import lambda.sprintdatapersistencejava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Transactional
@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{
	@Autowired
	private TodoRepository todoRepos;

	@Autowired
	private UserService userService;

	@Override
	public List<ToDo> findAll()
	{
		return null;
	}

	@Override
	public ToDo findTodoById(long id) throws EntityNotFoundException
	{
		return todoRepos.findById(id).orElseThrow(() -> new EntityNotFoundException((Long.toString(id))));
	}

	@Transactional
	@Override
	public ToDo save(ToDo todo)
	{
		ToDo newTodo = new ToDo();

		newTodo.setDescription(todo.getDescription());
		newTodo.setUser(todo.getUser());
		newTodo.setDateCreated(todo.getDateCreated());

		return todoRepos.save(newTodo);
	}

	@Transactional
	@Override
	public ToDo update(ToDo todo, long id)
	{
		ToDo currentTodo = findTodoById(id);

		if(todo.getDescription() != null)
		{
			currentTodo.setDescription(todo.getDescription());
		}

		//work in progress. Getting instance error of nothing to deserialize
		if(todo.getUser() != null)
		{
			User newUser = userService.findUserByUsername(todo.getUser().getUsername());
			currentTodo.setUser(newUser);
		}

		if(todo.isCompleted() != currentTodo.isCompleted())
		{
			currentTodo.setCompleted(todo.isCompleted());
		}
		return todoRepos.save(currentTodo);
	}
}
