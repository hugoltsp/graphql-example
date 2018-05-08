package com.teles.graphql.fetcher;

import java.util.List;

import org.springframework.stereotype.Component;

import com.teles.graphql.domain.Character;
import com.teles.graphql.repository.CharacterRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CharacterByFavoriteBeerFetcher implements DataFetcher<List<Character>> {

	private final CharacterRepository repository;

	public CharacterByFavoriteBeerFetcher(CharacterRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Character> get(DataFetchingEnvironment fetch) {
		return this.repository.findByFavoriteBeerLike(fetch.getArgument("beer"));
	}

}
