package lambda.sprintdatapersistencejava.service;

import lambda.sprintdatapersistencejava.model.ToDo;
import lambda.sprintdatapersistencejava.model.User;
import lambda.sprintdatapersistencejava.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{
	@Autowired
	private TodoRepository todoRepos;

	@Override
	public List<ToDo> findAll()
	{
		return null;
	}

	@Override
	public User findUserById(long id)
	{
		return null;
	}

	@Override
	public void delete(long id)
	{

	}

	@Override
	public User save(ToDo todo)
	{
		return null;
	}

	@Override
	public User update(ToDo user, long id)
	{
		return null;
	}
}
