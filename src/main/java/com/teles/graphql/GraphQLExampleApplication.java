package com.teles.graphql;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.javafaker.Faker;
import com.teles.graphql.domain.Character;
import com.teles.graphql.repository.CharacterRepository;

@SpringBootApplication
public class GraphQLExampleApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(GraphQLExampleApplication.class);
	
	private final CharacterRepository repository;

	public GraphQLExampleApplication(CharacterRepository repository) {
		this.repository = repository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GraphQLExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Creating fake data...");
		repository.save(Stream.of(gameOfThronesCharacters(), lordOfTheRingsCharacters(), witcherCharacters())
								.flatMap(Collection::stream)
								.collect(Collectors.toList())).forEach(this::log);
	}

	private List<Character> gameOfThronesCharacters() {
		return Stream.generate(new Faker().gameOfThrones()::character)
				.distinct()
				.limit(10)
				.map(this::createGotCharacter)
				.collect(Collectors.toList());
	}

	private List<Character> lordOfTheRingsCharacters() {
		return Stream.generate(new Faker().lordOfTheRings()::character)
				.distinct()
				.limit(10)
				.map(this::createLotrCharacter)
				.collect(Collectors.toList());
	}

	private List<Character> witcherCharacters() {
		return Stream.generate(new Faker().witcher()::character)
				.distinct()
				.limit(10)
				.map(this::createWitcherCharacter)
				.collect(Collectors.toList());
	}
	
	private Character createWitcherCharacter(String name) {
		Character character = fakeCharacter(name);
		character.setUniverse("The Witcher");
		return character;
	}

	private Character createLotrCharacter(String name) {
		Character character = fakeCharacter(name);
		character.setUniverse("Lord of The Rings");
		return character;
	}

	private Character createGotCharacter(String name) {
		Character character = fakeCharacter(name);
		character.setUniverse("Game of Thrones");
		return character;
	}

	private Character fakeCharacter(String name) {
		Character character = new Character();
		character.setName(name);
		character.setFavoriteBeer(new Faker().beer().name());
		return character;
	}
	
	private void log(Character c) {
		LOGGER.info(c.toString());
	}

}