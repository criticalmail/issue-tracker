package au.com.domain.issuetrackerdomain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import au.com.domain.issuetrackerdomain.model.Issue;


public interface IssueRepository extends CrudRepository<Issue, Long>
{

	@Query(value="SELECT * FROM ISSUE ", nativeQuery = true)
	List<Issue> getIssues();

	@Query(value="SELECT * FROM ISSUE WHERE title = ?1", nativeQuery = true)
	Issue findByTitle(String title);
	
} // End of interface
