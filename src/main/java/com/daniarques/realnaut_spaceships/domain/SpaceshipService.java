package com.daniarques.realnaut_spaceships.domain;

import com.daniarques.realnaut_spaceships.domain.model.Spaceship;

public interface SpaceshipService {


	Spaceship getSpaceshipById(Long id);

	void createSpaceship(Spaceship spaceship);

	void updateSpaceship(Long id, Spaceship spaceship);

	void deleteSpaceship(Long id);

}
