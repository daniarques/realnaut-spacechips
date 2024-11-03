package com.daniarques.realnaut_spaceships.domain;

import com.daniarques.realnaut_spaceships.domain.mapper.SpaceshipMapper;
import com.daniarques.realnaut_spaceships.domain.model.Spaceship;
import com.daniarques.realnaut_spaceships.repository.ShowRepository;
import com.daniarques.realnaut_spaceships.repository.SpaceshipRepository;
import com.daniarques.realnaut_spaceships.repository.entity.ShowEntity;
import com.daniarques.realnaut_spaceships.repository.entity.SpaceshipEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class SpaceshipServiceImpl implements SpaceshipService {

	private final SpaceshipRepository spaceshipRepository;
	private final ShowRepository showRepository;
	private final SpaceshipMapper spaceshipMapper;

	@Override
	public Spaceship getSpaceshipById(final Long id) {

		final Optional<SpaceshipEntity> entityFound = this.spaceshipRepository.findById(id);
		return entityFound.map(this.spaceshipMapper::map)
				// TODO 3/11/24: Throw custom exception
				.orElse(null);
	}

	@Override
	public List<Spaceship> findAllByNameContains(final String nameFilter) {

		final List<SpaceshipEntity> spaceShips = this.spaceshipRepository.findByNameContainingIgnoreCase(nameFilter);
		return spaceShips.stream()
				.map(this.spaceshipMapper::map)
				.toList();
	}

	@Override
	public void createSpaceship(final Spaceship spaceship) {

		this.saveSpaceship(spaceship);
	}

	@Override
	public void updateSpaceship(final Long id, final Spaceship spaceship) {

		if (!id.equals(spaceship.getId())) {
			return;
			// TODO 3/11/24: Throw custom exception
		}

		final Optional<SpaceshipEntity> spaceshipFound = this.spaceshipRepository.findById(id);

		if (spaceshipFound.isPresent()) {
			this.saveSpaceship(spaceship);
		}
		// TODO 3/11/24: Throw custom exception
	}

	@Override
	public void deleteSpaceship(final Long id) {

        this.spaceshipRepository.deleteById(id);

	}

	private void saveSpaceship(final Spaceship spaceship) {

		final ShowEntity show = this.showRepository.findByName(spaceship.getShow())
				.orElseGet(() -> ShowEntity.builder()
                .name(spaceship.getShow())
                .build());

		final SpaceshipEntity spaceshipToSave = this.spaceshipMapper.map(spaceship)
				.toBuilder()
				.show(show)
				.build();
		this.spaceshipRepository.save(spaceshipToSave);
	}
}
