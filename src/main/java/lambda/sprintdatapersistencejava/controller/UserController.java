package lambda.sprintdatapersistencejava.controller;

//import lambda.sprintdatapersistencejava.service.UserAuditing;
import lambda.sprintdatapersistencejava.model.User;
import lambda.sprintdatapersistencejava.repository.UserRepository;
import lambda.sprintdatapersistencejava.service.UserAuditing;
import lambda.sprintdatapersistencejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
