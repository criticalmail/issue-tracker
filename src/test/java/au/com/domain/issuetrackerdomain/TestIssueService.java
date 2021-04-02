package au.com.domain.issuetrackerdomain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import au.com.domain.issuetrackerdomain.model.Issue;
import au.com.domain.issuetrackerdomain.service.IssuePaginationService;
import au.com.domain.issuetrackerdomain.service.IssueService;


@SpringBootTest
public class TestIssueService {

	@Autowired
	IssueService service;
	
	@Autowired
	IssuePaginationService paginationService;
	
	@Test
	void issuesNotNull() {
	      Assertions.assertNotNull(service.getIssues() );
	}
	
	@Test
	void issuesNotEmpty() {
		Assertions.assertTrue(service.getIssues().size() > 0 );
	}
	
	@Test
	void createIssue() {
		Issue i = new Issue();
		i.setTitle("Create Issue from Josh");
		i.setDescription("new from josh desc");
		i.setStatus("done");
		i.setReporter(1);
		i.setAssignee(2);
		i.setCreated("2021-04-02");
		i.setCompleted("2021-04-02");

		Assertions.assertTrue( service.createIssue(i) );
	}
	
	@Test 
	void getById() {
		Assertions.assertNotNull( service.getIssueById(1) );
	}
	
	@Test 
	void updateExistingIssue() {
		
		Issue i = new Issue();
		i.setId(1);
		i.setTitle("Update Issue from Josh");
		i.setDescription("new from josh desc");
		i.setStatus("done");
		i.setReporter(1);
		i.setAssignee(2);
		i.setCreated("2021-04-02");
		i.setCompleted("2021-04-02");
		
		Assertions.assertTrue( service.update(i) );
	}
	
	@Test
	void deleteIssueById() {
		Assertions.assertTrue( service.deleteIssueById(2) );
	}
	
	@Test
	void filterByAssignee() {
		Assertions.assertNotNull(service.filterByAssignee("j.jones") );
		Assertions.assertTrue(service.filterByAssignee("j.jones").size() > 0 );
	}
	
	@Test
	void filterByInvalidAssignee() {
		Assertions.assertNotNull(service.filterByAssignee("Josh") );
		Assertions.assertFalse(service.filterByAssignee("Josh").size() > 0 );
	}

	@Test
	void filterByReporter() {
		Assertions.assertNotNull(service.filterByReporter("j.jones") );
		Assertions.assertTrue(service.filterByReporter("j.jones").size() > 0 );
	}
	
	@Test
	void filterByInvalidReporter() {
		Assertions.assertNotNull(service.filterByReporter("josh") );
		Assertions.assertFalse(service.filterByReporter("josh").size() > 0 );
	}
	
	@Test
	void filterByStatus() {
		Assertions.assertNotNull(service.filterIssuesByStatus("done") );
		Assertions.assertTrue(service.filterIssuesByStatus("done").size() > 0 );
	}
	
	@Test
	void filterByInvalidStatus() {
		Assertions.assertNotNull(service.filterIssuesByStatus("unknown") );
		Assertions.assertFalse(service.filterIssuesByStatus("unknown").size() > 0 );
	}
	
	@Test
	void filterByDates() {
		Assertions.assertNotNull(service.filterIssuesByDate("2017-07-22", "2017-08-02") );
		Assertions.assertTrue(service.filterIssuesByDate("2017-07-22", "2017-08-02").size() > 0 );
	}
	
	@Test
	void filterByInvalidDatesSamples() {
		Assertions.assertNotNull(service.filterIssuesByDate("2017-08-02", "2017-07-22") );
		Assertions.assertFalse(service.filterIssuesByDate("2017-08-02", "2017-07-22").size() > 0 );
	}
	
	@Test
	void sortByCreatedDate() {
		Assertions.assertNotNull(service.sortByCreatedDate("desc") );
		Assertions.assertTrue(service.sortByCreatedDate("desc").size() > 0 );
	}
	
	@Test
	void getIssueByPagination() {
		Assertions.assertNotNull(paginationService.getIssueByPagination(0, 2) );
		Assertions.assertTrue(paginationService.getIssueByPagination(0, 2).size() > 0 );
	}
	
	@Test
	void getIssueWithInvalidPagination() {
		Assertions.assertNotNull(paginationService.getIssueByPagination(0, 0) );
		Assertions.assertFalse(paginationService.getIssueByPagination(0, 0).size() > 0 );
	}
	
} // End of class
