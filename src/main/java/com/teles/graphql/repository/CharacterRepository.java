package com.teles.graphql.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.teles.graphql.domain.Character;

public interface CharacterRepository extends CrudRepository<Character, Long> {

	List<Character> findByUniverse(String universe);

	List<Character> findByNameLike(String name);

	List<Character> findByFavoriteBeerLike(String beer);

	List<Character> findAll();
}