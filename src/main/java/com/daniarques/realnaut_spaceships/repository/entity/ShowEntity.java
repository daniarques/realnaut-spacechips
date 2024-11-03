package com.daniarques.realnaut_spaceships.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@Entity(name = "show")
public class ShowEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	String name;
}
