package com.daniarques.realnaut_spaceships.domain.mapper;

import com.daniarques.realnaut_spaceships.domain.model.Spaceship;
import com.daniarques.realnaut_spaceships.repository.entity.SpaceshipEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpaceshipMapper {

	@Mapping(target = "show", source = "show.name")
	Spaceship map(SpaceshipEntity entity);

	@Mapping(target = "show", ignore = true)
	SpaceshipEntity map(Spaceship spaceship);

}
