package au.com.domain.issuetrackerdomain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import au.com.domain.issuetrackerdomain.model.Issue;
import au.com.domain.issuetrackerdomain.service.IssuePaginationService;
import au.com.domain.issuetrackerdomain.service.IssueService;

@RestController
public class IssueController {

	@Autowired
	IssueService service;
	
	@Autowired
	IssuePaginationService paginationService;
	
	@RequestMapping( 
			value = "/get-all-issues",
			method = RequestMethod.GET )
	@ResponseBody
	public List<Issue> getIssues() 
	{
		return service.getIssues();
	}
	
	@RequestMapping( 
			value = "/create-issues",
			method = RequestMethod.POST )
	@ResponseBody
	public boolean createIssue(@RequestBody Issue i) 
	{
		return service.createIssue(i);
	}

	@RequestMapping( 
			value = "/get-issue/{id}",
			method = RequestMethod.GET )
	@ResponseBody
	public Issue getIssueById(@PathVariable long id) 
	{
		return service.getIssueById(id);
	}
	
	@RequestMapping( 
			value = "/update-issue",
			method = RequestMethod.PUT )
	@ResponseBody
	public boolean updateIssue(Issue i) 
	{
		return service.update(i);
	}
	
	@RequestMapping( 
			value = "/delete-issue/{id}",
			method = RequestMethod.DELETE )
	@ResponseBody
	public boolean deleteIssueById(@PathVariable long id) 
	{
		return service.deleteIssueById(id);
	}
	
	@RequestMapping( 
			value = "/filter-by-assignee/{username}",
			method = RequestMethod.GET )
	@ResponseBody
	public List<Issue> filterByAssignee(@PathVariable String username) 
	{
		return service.filterByAssignee(username);
	}
	
	@RequestMapping( 
			value = "/filter-by-reporter/{username}",
			method = RequestMethod.GET )
	@ResponseBody
	public List<Issue> filterByReporter(@PathVariable String username) 
	{
		return service.filterByReporter(username);
	}
	
	@RequestMapping( 
			value = "/filter-by-status/{status}",
			method = RequestMethod.GET )
	@ResponseBody
	public List<Issue> filterIssuesByStatus(@PathVariable String status) 
	{
		return service.filterIssuesByStatus(status);
	}
	
	@RequestMapping( 
			value = "/filter-by-dates/{created}/{completed}",
			method = RequestMethod.GET )
	@ResponseBody
	public List<Issue> filterIssuesByDate(@PathVariable String created, @PathVariable String completed) 
	{
		return service.filterIssuesByDate(created, completed);
	}
	
	@RequestMapping( 
			value = "/sort-by-created/{sortOrder}",
			method = RequestMethod.GET )
	@ResponseBody
	public List<Issue> sortByCreatedDate(@PathVariable String sortOrder) 
	{
		return service.sortByCreatedDate(sortOrder);
	}
	
	@RequestMapping( 
			value = "/sort-by-page/{page}/{maxElements}",
			method = RequestMethod.GET )
	@ResponseBody
	public List<Issue> getIssueByPagination(@PathVariable int page, @PathVariable int maxElements) 
	{
		return paginationService.getIssueByPagination(page, maxElements);
	}
	
} // End of class
