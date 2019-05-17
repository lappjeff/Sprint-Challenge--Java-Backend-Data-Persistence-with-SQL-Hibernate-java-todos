package javatodos.repo;

import javatodos.models.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<ToDo, Long>
{
}
