package com.daniarques.realnaut_spaceships.domain;

import com.daniarques.realnaut_spaceships.domain.model.Spaceship;

import java.util.List;

public interface SpaceshipService {


	Spaceship getSpaceshipById(Long id);

    List<Spaceship> findAllByNameContains(String nameFilter);

    void createSpaceship(Spaceship spaceship);

	void updateSpaceship(Long id, Spaceship spaceship);

	void deleteSpaceship(Long id);

}
