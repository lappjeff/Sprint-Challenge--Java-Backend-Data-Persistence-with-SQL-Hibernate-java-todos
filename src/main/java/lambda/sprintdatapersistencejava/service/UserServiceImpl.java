package lambda.sprintdatapersistencejava.service;

import lambda.sprintdatapersistencejava.model.Role;
import lambda.sprintdatapersistencejava.model.ToDo;
import lambda.sprintdatapersistencejava.model.UserRoles;
import lambda.sprintdatapersistencejava.model.User;
import lambda.sprintdatapersistencejava.repository.RoleRepository;
import lambda.sprintdatapersistencejava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService
{

	@Autowired
	private UserRepository userRepos;
	@Autowired
	private RoleRepository roleRepos;

	@Override
	public User findUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = userRepos.findByUsername(username);
		if(user == null)
		{
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return user;
	}

	@Override
	public List<User> findAll()
	{
		ArrayList<User> users = new ArrayList<>();
		userRepos.findAll().iterator().forEachRemaining(users::add);
		return users;
	}

	@Override
	public User findUserById(long id) throws EntityNotFoundException
	{
		return userRepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
	}

	@Override
	public void delete(long id)
	{

	}

	@Transactional
	@Override
	public User save(User user)
	{
		User newUser = new User();

		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());

		ArrayList<UserRoles> newRoles = new ArrayList<>();


		Role newRole = roleRepos.findByRolename("admin");
		newRoles.add(new UserRoles(newUser, newRole));

		newUser.setUserRoles(newRoles);

		for(ToDo td: user.getTodos())
		{
			ToDo td1 = new ToDo(td.getDescription(), td.getDateCreated(), newUser);
			newUser.getTodos().add(td1);
		}

		return userRepos.save(newUser);
	}

	@Override
	public User update(User user, long id)
	{
		return null;
	}

	@Override
	public void addTodoToUser(ToDo todo, String username) throws EntityNotFoundException
	{
		User currentUser = findUserByUsername(username);

		currentUser.getTodos().add(new ToDo(todo.getDescription(), todo.getDateCreated(), currentUser));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = userRepos.findByUsername(username);

		if (user == null)
		{
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
	}
}