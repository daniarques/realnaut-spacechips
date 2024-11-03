package com.daniarques.realnaut_spaceships.repository.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@Entity(name = "spaceship")
public class SpaceshipEntity {

	@Id
	long id;

	String name;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "show_id", nullable = false)
	ShowEntity show;

}
