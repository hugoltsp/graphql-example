package com.teles.graphql.tests;

import com.teles.graphql.domain.Character;
import com.teles.graphql.fetcher.CharacterByUniverseFetcher;
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
public class CharacterByUniverseFetcherTest {

    @Mock
    private CharacterRepository characterRepository;

    private CharacterByUniverseFetcher characterByUniverseFetcher;

    @Before
    public void setUp() {
        this.characterByUniverseFetcher = new CharacterByUniverseFetcher(characterRepository);
    }

    @Test
    public void shouldReturnCharactersByUniverseWhenArgumentIsPresent() {

        List<Character> expectedCharacters = new ArrayList<>();

        Character character = new Character();
        character.setFavoriteBeer("Baden Baden Red Ale");
        character.setId(1L);
        character.setName("Jhon Snow");
        character.setUniverse("Game of Thrones");

        expectedCharacters.add(character);

        when(characterRepository.findByUniverse("Game of Thrones")).thenReturn(expectedCharacters);

        DataFetchingEnvironment dataFetchingEnvironment = mock(DataFetchingEnvironment.class);
        when(dataFetchingEnvironment.getArgument("universe")).thenReturn("Game of Thrones");

        assertEquals(expectedCharacters, characterByUniverseFetcher.get(dataFetchingEnvironment));
    }

    @Test
    public void shouldReturnEmptyResultWhenArgumentIsAbsent() {

        List<Character> characters = new ArrayList<>();

        Character character = new Character();
        character.setFavoriteBeer("Baden Baden Red Ale");
        character.setId(1L);
        character.setName("Jhon Snow");
        character.setUniverse("Game of Thrones");

        characters.add(character);

        when(characterRepository.findByUniverse("Game of Thrones")).thenReturn(characters);

        DataFetchingEnvironment dataFetchingEnvironment = mock(DataFetchingEnvironment.class);

        assertTrue(isEmpty(characterByUniverseFetcher.get(dataFetchingEnvironment)));
    }


}