package javatodos.todos.models;

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

	private
}
