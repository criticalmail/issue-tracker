package au.com.domain.issuetrackerdomain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User 
{
	@Id
	@Column(name="id")
	private long id;

	@Column(name="username")
	private String username;

	
	public User() {
		super();
	}

	public User(long id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
} // End of class
