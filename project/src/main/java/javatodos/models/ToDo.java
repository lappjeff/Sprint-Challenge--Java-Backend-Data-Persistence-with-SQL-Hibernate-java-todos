package javatodos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

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

	private String dateCreated;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	@JsonIgnoreProperties("todos")
	private User user;

	public ToDo()
	{
	}

	public ToDo(String description, String dateCreated, User user)
	{
		this.description = description;
		this.dateCreated = dateCreated;
		this.user = user;
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

	public String getDateCreated()
	{
		return dateCreated;
	}

	public void setDateCreated(String dateCreated)
	{
		this.dateCreated = dateCreated;
	}
}
