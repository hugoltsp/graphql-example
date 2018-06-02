package com.teles.graphql.tests;

import com.teles.graphql.domain.Character;
import com.teles.graphql.fetcher.CharacterListFetcher;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CharacterListFetcherTest {

    @Mock
    private CharacterRepository characterRepository;

    private CharacterListFetcher characterListFetcher;

    @Before
    public void setUp() {
        this.characterListFetcher = new CharacterListFetcher(characterRepository);
    }

    @Test
    public void shouldReturnAllCharactersList() {

        List<Character> expectedCharacters = new ArrayList<>();

        Character character = new Character();
        character.setFavoriteBeer("Baden Baden Red Ale");
        character.setId(1L);
        character.setName("Jon Snow");
        character.setUniverse("Game of Thrones");

        expectedCharacters.add(character);

        when(characterRepository.findAll()).thenReturn(expectedCharacters);

        DataFetchingEnvironment blankDataFetchingEnvironmentMock = mock(DataFetchingEnvironment.class);

        assertEquals(expectedCharacters, characterListFetcher.get(blankDataFetchingEnvironmentMock));
    }

}