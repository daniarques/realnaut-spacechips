package com.daniarques.realnaut_spaceships.domain.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Spaceship {

	long id;

	String name;

	String show;
}
