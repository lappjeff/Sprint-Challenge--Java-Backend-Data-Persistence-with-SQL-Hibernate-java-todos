package lambda.sprintdatapersistencejava.repository;

import lambda.sprintdatapersistencejava.model.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<ToDo, Long>
{
}
