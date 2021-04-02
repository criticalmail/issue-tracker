package au.com.domain.issuetrackerdomain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import au.com.domain.issuetrackerdomain.model.Issue;
import au.com.domain.issuetrackerdomain.repository.IssuePaginationRepository;

@Service
public class IssuePaginationService {

	@Autowired
	IssuePaginationRepository repository;
	
	public List<Issue> getIssueByPagination(int page, int maxElements) {
		
		if (  page < 0  || maxElements <= 0)
			return new ArrayList<>();
		
		Pageable pageWithElements = PageRequest.of(page, maxElements);

		return repository.findAll(pageWithElements).toList();
	}

} // End of class
