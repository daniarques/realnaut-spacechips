package com.daniarques.realnaut_spaceships.domain;

import com.daniarques.realnaut_spaceships.domain.mapper.ShowMapper;
import com.daniarques.realnaut_spaceships.domain.model.Show;
import com.daniarques.realnaut_spaceships.repository.ShowRepository;
import com.daniarques.realnaut_spaceships.repository.entity.ShowEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ShowService {

	private final ShowRepository showRepository;
	private final ShowMapper showMapper;

	public Show getShowById(final Long id) {
		final Optional<ShowEntity> entityFound = this.showRepository.findById(id);
		return entityFound.map(this.showMapper::map)
				// TODO 3/11/24: Throw custom exception
				.orElse(null);
	}
}
