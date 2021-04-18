package au.com.domain.issuetrackerdomain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig 
{
	@Bean
	public OpenAPI customOpenAPI(
			@Value("Issue Tracker API is an API service for issues") String appDesciption, 
			@Value("v1.2.0") String appVersion
			) {
		return new OpenAPI()
				.info(new Info()
						.title("Issue Tracker API")
						.version(appVersion)
						.description(appDesciption)
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

} // End of class
