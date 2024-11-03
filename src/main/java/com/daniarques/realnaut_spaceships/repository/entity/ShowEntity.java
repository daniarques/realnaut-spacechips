package com.daniarques.realnaut_spaceships.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ShowEntity {

	@Id
	long id;

	String name;
}
