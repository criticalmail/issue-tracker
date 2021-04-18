package au.com.domain.issuetrackerdomain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import au.com.domain.issuetrackerdomain.model.Issue;
import au.com.domain.issuetrackerdomain.service.IssuePaginationService;
import au.com.domain.issuetrackerdomain.service.IssueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = "/issue")
public class IssueController {

	@Autowired
	IssueService service;

	@Autowired
	IssuePaginationService paginationService;

	@Operation(summary = "Get an issue",
			description = "Retrieves and returns issue with id ")
	@ApiResponse(
			responseCode = "200", 
			description = "Success | OK",  
			content = @Content(
					schema = @Schema(implementation = Issue.class)
					, mediaType = "application/json")
			)
	//, content = @Content(mediaType = "text/plain")
	@GetMapping(
			path="/{id}", 
			produces = "application/json" )
	public Issue getIssueById(
			@Parameter(description="ID to be obtained. Cannot be empty.", required=true)
			@PathVariable long id) 
	{
		return service.getIssueById(id);
	}

	@Operation(summary = "Create an issue",
			description = "Create an issue ")
	@ApiResponse(
			responseCode = "201", 
			description = "Created",  
			content = @Content(
					mediaType = "text/plain")
			)
	@PostMapping(
			path="", 
			consumes = "application/json",
			produces = "application/json" )
	public boolean createIssue(
			@Parameter(description="Issue body to bo obtained. Cannot be empty.", required=true)
			@RequestBody Issue i
			) 
	{
		return service.createIssue(i);
	}

	@Operation(summary = "Update an issue",
			description = "Update an issue")
	@ApiResponse(
			responseCode = "200", 
			description = "Success | OK",  
			content = @Content(
					mediaType = "text/plain")
			)
	@PutMapping(
			path="", 
			consumes = "application/json" )
	public boolean updateIssue(
			@Parameter(description="Issue body to bo obtained. Cannot be empty.", required=true)
			@RequestBody Issue i) 
	{
		return service.update(i);
	}

	@Operation(summary = "Delete an issue",
			description = "Delete an issue")
	@ApiResponse(
			responseCode = "200", 
			description = "Success | OK",  
			content = @Content(
					mediaType = "text/plain")
			)
	@DeleteMapping(
			path="/{id}" )
	public boolean deleteIssueById(
			@Parameter(description="ID to be obtained. Cannot be empty.", required=true)
			@PathVariable long id
			) 
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
