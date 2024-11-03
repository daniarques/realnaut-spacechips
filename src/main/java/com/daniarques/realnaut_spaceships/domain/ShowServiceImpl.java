package com.daniarques.realnaut_spaceships.domain;

import com.daniarques.realnaut_spaceships.domain.mapper.ShowMapper;
import com.daniarques.realnaut_spaceships.domain.model.Show;
import com.daniarques.realnaut_spaceships.repository.ShowRepository;
import com.daniarques.realnaut_spaceships.repository.entity.ShowEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ShowServiceImpl implements ShowService {

	private final ShowRepository showRepository;
	private final ShowMapper showMapper;

	@Override
	public Show getShowById(final Long id) {

		final Optional<ShowEntity> entityFound = this.showRepository.findById(id);
		return entityFound.map(this.showMapper::map)
				// TODO 3/11/24: Throw custom exception
				.orElse(null);
	}

	@Override
	public Page<Show> getPaginatedShows(final Integer size, final Integer page) {

		final PageRequest pageable = PageRequest.of(page, size);
		final Page<ShowEntity> foundShowPage = this.showRepository.findAll(pageable);

		final List<Show> shows = foundShowPage.stream()
				.map(this.showMapper::map)
				.toList();

		return new PageImpl<>(shows, pageable, foundShowPage.getTotalElements());
	}
}
