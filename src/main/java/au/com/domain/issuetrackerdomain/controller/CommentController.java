package au.com.domain.issuetrackerdomain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import au.com.domain.issuetrackerdomain.model.Comment;
import au.com.domain.issuetrackerdomain.service.CommentService;

@RestController
public class CommentController 
{
	@Autowired
	CommentService service;
	
	@RequestMapping( 
			value = "/add-comment",
			method = RequestMethod.POST )
	@ResponseBody
	public boolean addComment(@RequestBody Comment comment) 
	{
		return service.addComment(comment);
	}
	
} // End of class
