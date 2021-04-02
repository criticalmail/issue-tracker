package au.com.domain.issuetrackerdomain.repository;

import org.springframework.data.repository.CrudRepository;

import au.com.domain.issuetrackerdomain.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long>
{
	
} // End of interface