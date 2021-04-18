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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import au.com.domain.issuetrackerdomain.model.Issue;
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

	@Operation(summary = "Get an issue",
			description = "Retrieves and returns issue with id ")
	@ApiResponse(
			responseCode = "200", 
			description = "Success | OK",  
			content = @Content(
					schema = @Schema(implementation = Issue.class)
					, mediaType = "application/json")
			)
	@GetMapping(
			path="/{id}", 
			produces = "application/json" )
	public Issue getIssueById(
			@Parameter(
					example= "1",
					description="ID to be obtained. Cannot be empty.", required=true)
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
			@Parameter(
					example="1", 
					description="ID to be obtained. Cannot be empty.", required=true)
			@PathVariable long id
			) 
	{
		return service.deleteIssueById(id);
	}

	//http://localhost:8080/v1/issue/filter?status=done&created=2017-07-01&completed=2017-08-30&assignee=h.humble&reporter=h.humble&order=desc
	@Operation(summary = "Filter issue",
			description = "Filtering issue with status, created date, completed date, assignee, reporter, order")
	@ApiResponse(
			responseCode = "200", 
			description = "Success | OK",  
			content = @Content(
					array = @ArraySchema(schema = @Schema(implementation = Issue.class)), 
					mediaType = "application/json"
					)
			)
	@GetMapping( 
			path = "/filter",
			produces = "application/json" )
	public List<Issue> filterIssue(
			@Parameter(example= "done", description="Status to bo obtained. Cannot be empty.", required=true)
			@RequestParam("status") String status,
			@Parameter(example="2017-07-01", description="Created date to bo obtained. Cannot be empty.", required=true)
			@RequestParam("created") String created,
			@Parameter(example="2017-08-30", description="Completed date to bo obtained. Cannot be empty.", required=true)
			@RequestParam("completed") String completed,
			@Parameter(example="h.humble", description="Assignee to bo obtained. Cannot be empty.", required=true)
			@RequestParam("assignee") String assignee,
			@Parameter(example="h.humble", description="Reproter to bo obtained. Cannot be empty.", required=true)
			@RequestParam("reporter") String reporter,
			@Parameter(example="desc or asc", description="Order to bo obtained. Cannot be empty. eg. desc = descending and asc = ascending", required=true)
			@RequestParam("order") String order
			) 
	{
		return service.filter(status,  created, completed, assignee, reporter, order);

	}
	
	@Operation(summary = "Get issues by pagination",
			description = "Get issues by pagination")
	@ApiResponse(
			responseCode = "200", 
			description = "Success | OK",  
			content = @Content(
					array = @ArraySchema(schema = @Schema(implementation = Issue.class)), 
					mediaType = "application/json"
					)
			)
	@GetMapping( 
			path = "/pagination",
			produces = "application/json" )
	public List<Issue> getIssueByPagination(
			@Parameter(example= "1", description="page to bo obtained. Cannot be empty.", required=true)
			@RequestParam("page") int page,
			@Parameter(example= "10", description="maximum element to bo obtained. Cannot be empty.", required=true)
			@RequestParam("max") int max) 
	{
		return service.getIssueByPagination(page, max);
	}

} // End of class
