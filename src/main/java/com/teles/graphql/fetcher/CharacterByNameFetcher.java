package com.teles.graphql.fetcher;

import java.util.List;

import org.springframework.stereotype.Component;

import com.teles.graphql.domain.Character;
import com.teles.graphql.repository.CharacterRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CharacterByNameFetcher implements DataFetcher<List<Character>> {

	private final CharacterRepository repository;

	public CharacterByNameFetcher(CharacterRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Character> get(DataFetchingEnvironment fetch) {
		return repository.findByNameLike(fetch.getArgument("name"));
	}

}
