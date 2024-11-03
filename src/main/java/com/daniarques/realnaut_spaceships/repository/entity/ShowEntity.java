package com.daniarques.realnaut_spaceships.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "show")
public class ShowEntity {

	@Id
	long id;

	String name;
}
