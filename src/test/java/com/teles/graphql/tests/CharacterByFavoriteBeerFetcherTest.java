package com.teles.graphql.tests;

import com.teles.graphql.domain.Character;
import com.teles.graphql.fetcher.CharacterByFavoriteBeerFetcher;
import com.teles.graphql.repository.CharacterRepository;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.util.CollectionUtils.isEmpty;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CharacterByFavoriteBeerFetcherTest {

    @Mock
    private CharacterRepository characterRepository;

    private CharacterByFavoriteBeerFetcher characterByFavoriteBeerFetcher;

    @Before
    public void setUp() {
        characterByFavoriteBeerFetcher = new CharacterByFavoriteBeerFetcher(characterRepository);
    }

    @Test
    public void shouldReturnCharactersByFavoriteBeerWhenArgumentIsPresent() {
        List<Character> expectedCharacters = new ArrayList<>();

        Character character = new Character();
        character.setFavoriteBeer("Baden Baden Red Ale");
        character.setId(1L);
        character.setName("Jon Snow");
        character.setUniverse("Game of Thrones");

        expectedCharacters.add(character);

        when(characterRepository.findByFavoriteBeerContaining("Baden")).thenReturn(expectedCharacters);

        DataFetchingEnvironment dataFetchingEnvironment = mock(DataFetchingEnvironment.class);
        when(dataFetchingEnvironment.getArgument("favoriteBeer")).thenReturn("Baden");

        assertEquals(expectedCharacters, characterByFavoriteBeerFetcher.get(dataFetchingEnvironment));
    }

    @Test
    public void shouldReturnEmptyResultWhenArgumentIsAbsent() {

        List<Character> characters = new ArrayList<>();

        Character character = new Character();
        character.setFavoriteBeer("Baden Baden Red Ale");
        character.setId(1L);
        character.setName("Jon Snow");
        character.setUniverse("Game of Thrones");

        characters.add(character);

        when(characterRepository.findByFavoriteBeerContaining("Baden")).thenReturn(characters);

        DataFetchingEnvironment dataFetchingEnvironment = mock(DataFetchingEnvironment.class);

        assertTrue(isEmpty(characterByFavoriteBeerFetcher.get(dataFetchingEnvironment)));
    }

}