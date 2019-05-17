package javatodos.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "todos")
public class ToDo extends Auditable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long todoid;

	@Column(nullable = false)
	private String description;

	private boolean completed = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	@JsonIgnoreProperties("todos")
	private User user;

	public ToDo()
	{
	}

	public ToDo(String description)
	{
		this.description = description;
	}

	public long getTodoid()
	{
		return todoid;
	}

	public void setTodoid(long todoid)
	{
		this.todoid = todoid;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public boolean isCompleted()
	{
		return completed;
	}

	public void setCompleted(boolean completed)
	{
		this.completed = completed;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}
