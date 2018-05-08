package com.teles.graphql.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Character {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String universe;

	private String favoriteBeer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUniverse() {
		return universe;
	}

	public void setUniverse(String universe) {
		this.universe = universe;
	}

	public String getFavoriteBeer() {
		return favoriteBeer;
	}

	public void setFavoriteBeer(String favoriteBeer) {
		this.favoriteBeer = favoriteBeer;
	}

}