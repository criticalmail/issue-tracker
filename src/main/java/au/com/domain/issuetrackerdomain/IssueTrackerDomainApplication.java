package au.com.domain.issuetrackerdomain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class IssueTrackerDomainApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssueTrackerDomainApplication.class, args);
	}
    
} // End of class
