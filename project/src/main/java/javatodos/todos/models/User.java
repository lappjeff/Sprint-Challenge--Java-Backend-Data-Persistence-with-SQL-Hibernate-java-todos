package javatodos.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class User extends Auditable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userid;

	@Column(nullable = false,
			unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@JsonIgnore
	@OneToMany(mappedBy = "user",
			   cascade = CascadeType.ALL,
			   orphanRemoval = true,
			   fetch = FetchType.LAZY)
	@JsonIgnoreProperties("user")
	private List<ToDo> todos = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties({"role", "user"})
	private List<UserRoles> userRoles = new ArrayList<>();

	public User()
	{
	}

	public User(String username, String password, List<UserRoles> userRoles)
	{
		this.username = username;
		setPassword(password);

		for (UserRoles ur : userRoles)
		{
			ur.setUser(this);
		}
		this.userRoles = userRoles;
	}

	public long getUserid()
	{
		return userid;
	}

	public void setUserid(long userid)
	{
		this.userid = userid;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.password = passwordEncoder.encode(password);
	}

	public List<UserRoles> getUserRoles()
	{
		return userRoles;
	}

	public void setUserRoles(List<UserRoles> userRoles)
	{
		this.userRoles = userRoles;
	}

	public List<ToDo> getTodos()
	{
		return todos;
	}

	public void setTodos(List<ToDo> todos)
	{
		this.todos = todos;
	}

	public List<SimpleGrantedAuthority> getAuthority()
	{
		List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

		for (UserRoles r : this.userRoles)
		{
			String myRole = "ROLE_" + r.getRole().getName().toUpperCase();
			rtnList.add(new SimpleGrantedAuthority(myRole));
		}
		return rtnList;
	}
}
