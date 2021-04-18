package au.com.domain.issuetrackerdomain.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import au.com.domain.issuetrackerdomain.model.Issue;


public interface IssueRepository extends CrudRepository<Issue, Long>
{
	
	@Query(value="SELECT * FROM ISSUE WHERE title = ?1", nativeQuery = true)
	Issue findByTitle(String title);

	@Query(value="SELECT * FROM ISSUE WHERE assignee = ?1", nativeQuery = true)
	List<Issue> filterByAssignee(long assignee);

	@Query(value="SELECT * FROM ISSUE WHERE status = ?1 AND created >= ?2 AND completed <= ?3 AND assignee = ?4 AND reporter = ?5", nativeQuery = true)
	List<Issue>  filter(String status, String created, String completed, long assignee, long reporter);
	
} // End of interface
