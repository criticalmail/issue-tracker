package au.com.domain.issuetrackerdomain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import au.com.domain.issuetrackerdomain.model.User;

public interface UserRepository extends CrudRepository<User, Long>
{

	@Query(value="SELECT id, username FROM USER WHERE username = ?1", nativeQuery = true)
	User getUserByUsername(String username);
	
//	List<User> findByUsername(String username);
	
} // End of interface
