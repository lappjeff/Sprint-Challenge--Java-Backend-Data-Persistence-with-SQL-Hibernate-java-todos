package javatodos.todos.service;

import javatodos.todos.models.User;
import javatodos.todos.repo.RoleRepository;
import javatodos.todos.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
	public User findUserById(long id)
	{
		return null;
	}

	@Override
	public void delete(long id)
	{

	}

	@Override
	public User save(User user)
	{
		return null;
	}

	@Override
	public User update(User user, long id)
	{
		return null;
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
