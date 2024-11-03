package com.daniarques.realnaut_spaceships.domain;

import com.daniarques.realnaut_spaceships.domain.model.Show;
import org.springframework.data.domain.Page;

public interface ShowService {

	Show getShowById(Long id);

    Page<Show> getPaginatedShows(Integer size, Integer page);
}
