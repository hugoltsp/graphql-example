package com.teles.graphql.fetcher;

import java.util.List;

import org.springframework.stereotype.Component;

import com.teles.graphql.repository.CharacterRepository;
import com.teles.graphql.domain.Character;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CharacterListFetcher implements DataFetcher<List<Character>> {

	private final CharacterRepository repository;

	public CharacterListFetcher(CharacterRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Character> get(DataFetchingEnvironment fetch) {
		return repository.findAll();
	}

}
