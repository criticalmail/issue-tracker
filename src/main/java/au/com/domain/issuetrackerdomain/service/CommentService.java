package au.com.domain.issuetrackerdomain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.domain.issuetrackerdomain.model.Comment;
import au.com.domain.issuetrackerdomain.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository repository;
	
	public List<Comment> getAllComments() {
		return (List<Comment>) repository.findAll();
	}

	public boolean addComment(Comment comment) {
		repository.save(comment);
		return true;
	}
	

} // End of class
