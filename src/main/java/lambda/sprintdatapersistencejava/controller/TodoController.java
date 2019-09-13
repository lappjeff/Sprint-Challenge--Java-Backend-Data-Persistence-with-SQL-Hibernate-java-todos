package lambda.sprintdatapersistencejava.controller;

import lambda.sprintdatapersistencejava.model.ToDo;
import lambda.sprintdatapersistencejava.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController
{
	@Autowired
	private TodoService todoService;

	//localhost:2019/todos/todoid
	@PutMapping(value = "/{todoid}", consumes = "application/json")
	public ResponseEntity<?> updateTodo(@RequestBody ToDo updateTodo, @PathVariable long todoid)
	{
		todoService.update(updateTodo, todoid);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
