package javatodos.todos.repo;

import javatodos.todos.models.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<ToDo, Long>
{
}
