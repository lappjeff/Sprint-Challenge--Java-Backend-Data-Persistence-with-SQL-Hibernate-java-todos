package lambda.sprintdatapersistencejava.controller;

//import lambda.sprintdatapersistencejava.service.UserAuditing;
import lambda.sprintdatapersistencejava.model.ToDo;
import lambda.sprintdatapersistencejava.model.User;
import lambda.sprintdatapersistencejava.repository.UserRepository;
import lambda.sprintdatapersistencejava.service.UserAuditing;
import lambda.sprintdatapersistencejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController
{
	@Autowired
	private UserService userService;

	//localhost:2019/users/current
	@GetMapping(value = "/current",
				produces = {"application/json"})
	public ResponseEntity<?> getCurrentUser(Authentication authentication)
	{
		return new ResponseEntity<>((authentication.getPrincipal()), HttpStatus.OK);
	}

	//localhost:2019/users/viewall
	@GetMapping(value = "/viewall", produces = {"application/json"})
	public ResponseEntity<?> listAllUsers()
	{
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}

	//localhost:2019/users/add
	@PostMapping(value = "/add", consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<?> addUser(@Valid
									 @RequestBody
											 User newUser)
	{
		userService.save(newUser);

		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}

	//localhost:2019/users/todo/{userid}
	@PostMapping(value = "/todo/{username}", consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<?> addTodoToUser(@Valid @RequestBody ToDo newTodo, @PathVariable String username)
	{
		userService.addTodoToUser(newTodo, username);

		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}

	//localhost:2019/users/delete/userid
	@DeleteMapping(value = "/delete/{userid}")
	public ResponseEntity<?> deleteUser(@PathVariable long userid)
	{
		userService.delete(userid);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
