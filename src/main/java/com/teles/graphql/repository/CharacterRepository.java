package com.teles.graphql.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.teles.graphql.domain.Character;

public interface CharacterRepository extends CrudRepository<Character, Long> {

	List<Character> findByUniverse(String universe);

	List<Character> findByNameContaining(String name);

	List<Character> findByFavoriteBeerContaining(String beer);

	List<Character> findAll();
}