package javatodos.todos.controllers;

import javatodos.todos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController
{
	@Autowired
	private UserService userService;

	//localhost:2018/users/current
	@GetMapping(value = "/current",
				produces = {"application/json"})
	public ResponseEntity<?> getCurrentUser()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		System.out.println("TRIGGERED " + userName);
		return new ResponseEntity<>(userService.findUserByUsername(userName), HttpStatus.OK);
	}

	//localhost:2018/users/viewall
	@GetMapping(value = "/viewall", produces = {"application/json"})
	public ResponseEntity<?> listAllUsers()
	{
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}
}
