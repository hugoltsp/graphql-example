package com.teles.graphql.config;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {

		return new Docket(SWAGGER_2)
				.select()
				.apis(withClassAnnotation(Api.class))
				.paths(any())
				.build()
				.pathMapping("/");
	}
	
}
