package com.teles.graphql;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.generate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.javafaker.Faker;
import com.teles.graphql.domain.Character;
import com.teles.graphql.repository.CharacterRepository;

@SpringBootApplication
public class GraphQLExampleApplication implements CommandLineRunner {

	private final CharacterRepository repository;

	public GraphQLExampleApplication(CharacterRepository repository) {
		this.repository = repository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GraphQLExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.save(createCharacters());
	}

	private List<Character> createCharacters() {
		List<Character> characters = new ArrayList<>();
		
		characters.addAll(gameOfThronesCharacters());
		characters.addAll(lordOfTheRingsCharacters());
		characters.addAll(witcherCharacters());

		return characters;
	}

	private List<Character> gameOfThronesCharacters() {
		return generate(new Faker().gameOfThrones()::character)
				.distinct()
				.limit(10)
				.map(this::got)
				.collect(toList());
	}

	private List<Character> lordOfTheRingsCharacters() {
		return generate(new Faker().lordOfTheRings()::character)
				.distinct()
				.limit(10)
				.map(this::lotr)
				.collect(toList());
	}

	private List<Character> witcherCharacters() {
		return generate(new Faker().witcher()::character)
				.distinct()
				.limit(10)
				.map(this::witcher)
				.collect(toList());
	}
	
	private Character got(String name) {
		Character character = fakeCharacter(name);
		character.setUniverse("Game Of Thrones");
		return character;
	}


	private Character lotr(String name) {
		Character character = fakeCharacter(name);
		character.setUniverse("Lord of The Rings");
		return character;
	}
	
	private Character witcher(String name) {
		Character character = fakeCharacter(name);
		character.setUniverse("The Witcher");
		return character;
	}
	
	private Character fakeCharacter(String name) {
		Character character = new Character();
		character.setName(name);
		character.setFavoriteBeer(new Faker().beer().name());
		return character;
	}
	
}