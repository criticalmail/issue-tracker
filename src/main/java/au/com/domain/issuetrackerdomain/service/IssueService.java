package au.com.domain.issuetrackerdomain.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.domain.issuetrackerdomain.model.Issue;
import au.com.domain.issuetrackerdomain.model.User;
import au.com.domain.issuetrackerdomain.repository.IssueRepository;
import au.com.domain.issuetrackerdomain.repository.UserRepository;

@Service
public class IssueService {

	@Autowired
	IssueRepository iRepository;

	@Autowired
	UserRepository uRepository;

	// Get all Issues
	public List<Issue> getIssues(){
		//return (List<Issue>) repository.findAll();
		return iRepository.getIssues();
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

	// Filter by assignedd
	public List<Issue> filterByAssignee(String username) {
		User user = this.getUserByUsername(username);

		// User not found
		if ( user == null )
			return new ArrayList<>(); // empty list of []

		return iRepository.getIssues()
				.parallelStream()
				.filter( i ->  i.getAssignee() == user.getId())
				.collect( Collectors.toList());
	}

	// Filter by reporter
	public List<Issue> filterByReporter(String username) {
		User user = this.getUserByUsername(username);

		// User not found
		if ( user == null)
			return new ArrayList<>(); // empty list of []

		return iRepository.getIssues()
				.parallelStream()
				.filter( i ->  i.getReporter() == user.getId())
				.collect( Collectors.toList());
	}

	// Filter issues by status
	public List<Issue> filterIssuesByStatus(String status) {
		return iRepository.getIssues()
				.parallelStream()
				.filter( i -> i.getStatus().equals(status))
				.collect(Collectors.toList());
	}

	// Filter issues by dates 
	public List<Issue> filterIssuesByDate(String created, String completed) {
		long requestedCreated = this.convertDateToUnix(created);
		long requestedCompleted = this.convertDateToUnix(completed);

		return iRepository.getIssues().parallelStream()
				.filter(i -> requestedCreated >= this.convertDateToUnix(i.getCreated()) )
				.filter(i -> requestedCompleted <= this.convertDateToUnix(i.getCompleted()) )
				.collect( Collectors.toList() );

	}

	// Sort By create date
	public List<Issue> sortByCreatedDate(String sortOrder) {
		List<Issue> issues = iRepository.getIssues();
	     
		if ( sortOrder.equals("desc") )
			Collections.sort( issues, new CreatedComparator().reversed() );
		else 
			Collections.sort( issues, new CreatedComparator() );
		
		return issues;
	}

	// Get user by username
	private User getUserByUsername(String username) {
		return uRepository.getUserByUsername(username);
	}

	// Convert Date to Unix Time
	private long convertDateToUnix(String dateString) {
		try {
			if (dateString != null) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
				Date date = dateFormat.parse(dateString);
				return (long) date.getTime()/1000;
			}
		} catch (Exception e) {
			System.out.println("Err -> " + e.getMessage());
		}
		return 0;
	}

} // End of class
