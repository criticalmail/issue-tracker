package au.com.domain.issuetrackerdomain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.domain.issuetrackerdomain.model.Comment;
import au.com.domain.issuetrackerdomain.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = "/comment")
public class CommentController 
{
	@Autowired
	CommentService service;
	
	/**
	 * Add comment
	 * @param comment
	 * @return
	 */
	@Operation(summary = "Create a comment",
			description = "Create a comment")
	@ApiResponse(
			responseCode = "201", 
			description = "Created",  
			content = @Content(
					mediaType = "text/plain")
			)
	@PostMapping(
			path="", 
			consumes = "application/json",
			produces = "text/plain" )
	public boolean addComment(@RequestBody Comment comment) 
	{
		return service.addComment(comment);
	}
	
} // End of class
