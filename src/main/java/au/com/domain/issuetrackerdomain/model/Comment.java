package au.com.domain.issuetrackerdomain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COMMENT")
public class Comment 
{
	@Id
	@Column(name="id")
	private long id;

	@Column(name="issue")
	private long issue;
	
	@Column(name="by")
	private long by;
	
	@Column(name="created")
	private String created;

	
	public Comment() {
		super();
	}

	public Comment(long id, long issue, long by, String created) {
		super();
		this.id = id;
		this.issue = issue;
		this.by = by;
		this.created = created;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIssue() {
		return issue;
	}

	public void setIssue(long issue) {
		this.issue = issue;
	}

	public long getBy() {
		return by;
	}

	public void setBy(long by) {
		this.by = by;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
	
	
} // End of class
