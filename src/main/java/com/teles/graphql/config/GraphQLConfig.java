package com.teles.graphql.config;

import com.teles.graphql.fetcher.CharacterByFavoriteBeerFetcher;
import com.teles.graphql.fetcher.CharacterByNameFetcher;
import com.teles.graphql.fetcher.CharacterByUniverseFetcher;
import com.teles.graphql.fetcher.CharacterListFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeRuntimeWiring.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Configuration
public class GraphQLConfig {

	private final Resource resource;

	private final CharacterByUniverseFetcher byUniverseFetcher;

	private final CharacterByNameFetcher byNameFetcher;

	private final CharacterByFavoriteBeerFetcher byFavoriteBeerFetcher;

	private final CharacterListFetcher listFetcher;

	public GraphQLConfig(@Value("classpath:characters.graphql") Resource resource,
			CharacterByUniverseFetcher byUniverseFetcher,
			CharacterByNameFetcher byNameFetcher,
			CharacterByFavoriteBeerFetcher byFavoriteBeerFetcher,
			CharacterListFetcher listFetcher) {
		this.resource = resource;
		this.byUniverseFetcher = byUniverseFetcher;
		this.byNameFetcher = byNameFetcher;
		this.byFavoriteBeerFetcher = byFavoriteBeerFetcher;
		this.listFetcher = listFetcher;
	}

    @Bean
    public GraphQL buildGraphQL() throws IOException {
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring().type("Query", this::apply).build();
        GraphQLSchema graphQLSchema = new SchemaGenerator()
                .makeExecutableSchema(new SchemaParser()
                        .parse(new BufferedReader(new InputStreamReader(resource.getInputStream()))), runtimeWiring);
        return GraphQL.newGraphQL(graphQLSchema).build();
    }

	private Builder apply(Builder typeWiring) {
		return typeWiring.dataFetcher("byName", byNameFetcher)
				.dataFetcher("byUniverse", byUniverseFetcher)
				.dataFetcher("byFavoriteBeer", byFavoriteBeerFetcher)
				.dataFetcher("all", listFetcher);
	}
}
