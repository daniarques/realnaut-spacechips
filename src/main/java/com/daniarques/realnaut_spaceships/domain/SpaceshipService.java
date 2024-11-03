package com.daniarques.realnaut_spaceships.domain;

import com.daniarques.realnaut_spaceships.domain.model.Spaceship;
import org.springframework.data.domain.Page;

public interface SpaceshipService {


    Spaceship getSpaceshipById(Long id);

    Page<Spaceship> findAllByFilter(Integer size, Integer page, String nameFilter);

    void createSpaceship(Spaceship spaceship);

    void updateSpaceship(Long id, Spaceship spaceship);

    void deleteSpaceship(Long id);

}
