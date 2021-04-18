package au.com.domain.issuetrackerdomain.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import au.com.domain.issuetrackerdomain.model.Issue;
import au.com.domain.issuetrackerdomain.model.User;
import au.com.domain.issuetrackerdomain.repository.IssuePaginationRepository;
import au.com.domain.issuetrackerdomain.repository.IssueRepository;
import au.com.domain.issuetrackerdomain.repository.UserRepository;

@Service
public class IssueService {

	@Autowired
	IssueRepository iRepository;

	@Autowired
	UserRepository uRepository;

	@Autowired
	IssuePaginationRepository paginationRepository;

	private static DateValidator validator;

	@PostConstruct
	public void init()
	{
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.US)
				.withResolverStyle(ResolverStyle.STRICT);
		validator = new DateValidator(dateFormatter);
	}

	// Create Issue
	public boolean createIssue(Issue issue) {

		// Title exists and avoid duplication in db
		if ( iRepository.findByTitle(issue.getTitle()) != null )
			return false;

		iRepository.save(issue); // save

		// The issue can be retrieved 
		if ( iRepository.findByTitle(issue.getTitle()) != null )
			return true;

		return false;
	}

	// Get Issue By ID
	public Issue getIssueById(long id) {
		return iRepository.findById(id).get();
	}

	// Update an issue
	public boolean update(Issue issue) {
		Issue retrieved = iRepository.findById(issue.getId()).get();

		// Issue not found
		if ( retrieved == null )
			return false;

		retrieved.setTitle(issue.getTitle());
		retrieved.setDescription(issue.getDescription());
		retrieved.setStatus(issue.getStatus());
		retrieved.setReporter(issue.getReporter());
		retrieved.setAssignee(issue.getAssignee());
		retrieved.setCreated(issue.getCreated());
		retrieved.setCompleted(issue.getCompleted());

		iRepository.save(retrieved); // save updated data
		return true;
	}

	// Delete issue by id
	public boolean deleteIssueById(long id) {
		Issue retrieved = iRepository.findById(id).get();

		// Issue not found
		if ( retrieved == null )
			return false;

		iRepository.delete(retrieved); // delete
		return true;
	}
	
	// Filter issues
	public List<Issue> filter(
			String status, 
			String created, 
			String completed, 
			String assignee, 
			String reporter,
			String order ) {

		// Check status
		if ( status == null || status.length() == 0 || ( !status.equals("done") && !status.equals("in_progress")) )
			return new ArrayList<>(); // empty list of []
		
		// Check assignee
		User assigneeUser = this.getUserByUsername(assignee);
		if ( assigneeUser == null )
			return new ArrayList<>(); // empty list of []

		// Check reporter
		User reporterUser = this.getUserByUsername(reporter);
		if ( reporterUser == null )
			return new ArrayList<>(); // empty list of []

		// Check created and completed date
		if ( !validator.isValid(created) || !validator.isValid(completed) )
			return new ArrayList<>(); // empty list of []
		
		// Check order
		if ( order == null || order.length() == 0 || ( !order.equals("asc") && !order.equals("desc")) )
			return new ArrayList<>(); // empty list of []
		
		List<Issue> result = iRepository.filter(status,  created, completed, assigneeUser.getId(), reporterUser.getId());

		if ( result.size() == 0 )
			return new ArrayList<>(); // empty list of []

		if ( order.equals("desc") )
			Collections.sort( result, new CreatedComparator().reversed() );
		else 
			Collections.sort( result, new CreatedComparator() );

		return result;
	}

	// Get issue by pagination
	public List<Issue> getIssueByPagination(int page, int maxElements) {

		if (  page < 0  || maxElements <= 0)
			return new ArrayList<>();

		Pageable pageWithElements = PageRequest.of(page, maxElements);

		return paginationRepository.findAll(pageWithElements).toList();
	}

	// Get user by username
	private User getUserByUsername(String username) {
		return uRepository.getUserByUsername(username);
	}

	@PreDestroy
	public void destroy()
	{
		validator = null;
	}


} // End of class
