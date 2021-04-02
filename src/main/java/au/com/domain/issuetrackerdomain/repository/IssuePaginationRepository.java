package au.com.domain.issuetrackerdomain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import au.com.domain.issuetrackerdomain.model.Issue;

public interface IssuePaginationRepository extends PagingAndSortingRepository<Issue, Long> 
{
	

} // End of interface
