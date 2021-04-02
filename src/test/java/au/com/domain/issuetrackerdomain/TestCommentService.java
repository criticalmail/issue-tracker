package au.com.domain.issuetrackerdomain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import au.com.domain.issuetrackerdomain.model.Comment;
import au.com.domain.issuetrackerdomain.service.CommentService;

@SpringBootTest
public class TestCommentService {

	@Autowired
	CommentService service;
	
	@Test
	void getAllComments() {
		Assertions.assertNotNull(service.getAllComments());
		Assertions.assertTrue(service.getAllComments().size() > 0 );
	}
	
	@Test
	void addComment() {
		Comment c = new Comment();
		c.setIssue(2);
		c.setBy(1);
		c.setCreated("2017-08-01");
		
		Assertions.assertTrue(service.addComment(c) );
	}
	
} // End od class
