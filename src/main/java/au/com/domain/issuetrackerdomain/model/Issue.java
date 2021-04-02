package au.com.domain.issuetrackerdomain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ISSUE")
public class Issue 
{
	@Id
	@Column(name="id")
	private long id;

	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="status")
	private String status;
	
	@Column(name="reporter")
	private long reporter;
	
	@Column(name="assignee")
	private long assignee;
	
	@Column(name="created")
	private String created;
	
	@Column(name="completed")
	private String completed;
	
	public Issue() {
		super();
	}
	

	public Issue(long id, String title, String description, String status, long reporter, long assignee,
			String created, String completed) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.reporter = reporter;
		this.assignee = assignee;
		this.created = created;
		this.completed = completed;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getReporter() {
		return reporter;
	}

	public void setReporter(long reporter) {
		this.reporter = reporter;
	}

	public long getAssignee() {
		return assignee;
	}

	public void setAssignee(long assignee) {
		this.assignee = assignee;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}


} // End of class
