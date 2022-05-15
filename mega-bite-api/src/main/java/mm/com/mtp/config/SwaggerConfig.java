package mm.com.mtp.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 configuration for REST APIs.
 * 
 * @author Nay Myo Htet
 *
 * @since Sep 16, 2019
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket focusBeautyApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any()) // RequestHandlerSelectors.basePackage("mm.com.xan.focusbeauty.controller")
				.paths(paths()) // regex("/api/v1/brands")
				.build();
	}

	// API description
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Focus Beauty APIs")
				.description("This page lists all the rest apis for Focus Beauty System.")
				.version("1.0-SNAPSHOT")
				.build();
	}

	// Only select APIs that matches the given Predicates.
	private Predicate<String> paths() {
		// Match all paths except /error
		return Predicates.and(
				PathSelectors.regex("/.*"),
				Predicates.not(PathSelectors.regex("/error.*")));
	}

}
