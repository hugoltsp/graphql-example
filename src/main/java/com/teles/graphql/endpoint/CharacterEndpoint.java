package com.teles.graphql.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teles.graphql.repository.CharacterRepository;

import graphql.ExecutionResult;
import graphql.GraphQL;
import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/character")
public class CharacterEndpoint {

	private final CharacterRepository repository;
	private final GraphQL graphQL;

	public CharacterEndpoint(CharacterRepository repository, GraphQL graphQL) {
		this.repository = repository;
		this.graphQL = graphQL;
	}

	@PostMapping(value = "/graphql")
	public ResponseEntity<?> postMethodName(@RequestBody String body) {
		ExecutionResult executionResult = graphQL.execute(body);
		return ResponseEntity.ok(executionResult.getData());
	}

	@GetMapping
	public ResponseEntity<?> get() {
		return ResponseEntity.ok(this.repository.findAll());
	}
}
