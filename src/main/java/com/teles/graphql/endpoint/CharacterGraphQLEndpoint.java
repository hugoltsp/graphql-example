package com.teles.graphql.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.GraphQL;
import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/character")
public class CharacterGraphQLEndpoint {

	private final GraphQL graphQL;

	public CharacterGraphQLEndpoint(GraphQL graphQL) {
		this.graphQL = graphQL;
	}

	@PostMapping("/graphql")
	public ResponseEntity<?> postGraphql(@RequestBody String body) {
		return ResponseEntity.ok(graphQL.execute(body).getData());
	}

}
