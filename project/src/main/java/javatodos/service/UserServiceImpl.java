package javatodos.service;

import javatodos.models.ToDo;
import javatodos.models.User;
import javatodos.models.UserRoles;
import javatodos.repo.RoleRepository;
import javatodos.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
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

	@Override
	public User save(User user)
	{
		User newUser = new User();

		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());

		for(UserRoles ur : user.getUserRoles())
		{
			newUser.getUserRoles().add(new UserRoles(ur.getUser(), ur.getRole()));
		}

		for(ToDo td: user.getTodos())
		{
			ToDo td1 = new ToDo(td.getDescription(), "Todoy", newUser);
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
	public void addTodoToUser(ToDo todo, long userid) throws EntityNotFoundException
	{
		User currentUser = findUserById(userid);

		if(currentUser == null)
		{
			throw new EntityNotFoundException("User with ID " + userid + " not found");
		}

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
