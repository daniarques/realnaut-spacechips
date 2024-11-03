package com.daniarques.realnaut_spaceships.domain;

import com.daniarques.realnaut_spaceships.domain.mapper.ShowMapperImpl;
import com.daniarques.realnaut_spaceships.domain.model.Show;
import com.daniarques.realnaut_spaceships.repository.ShowRepository;
import com.daniarques.realnaut_spaceships.repository.entity.ShowEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ShowServiceImplTest {

	private static final long ID = 1L;
	private static final String SHOW_NAME = "Show name";
	@Mock
	private ShowRepository showRepository;

	@Spy
	private ShowMapperImpl showMapper;

	@InjectMocks
	private ShowServiceImpl showService;

	@Test
	void when_getShowById_found_should_getShow() {
		final ShowEntity showEntity = ShowEntity.builder()
				.id(ID)
				.name(SHOW_NAME)
				.build();
		given(this.showRepository.findById(ID)).willReturn(Optional.of(showEntity));

		final Show actualShow = this.showService.getShowById(ID);

		assertThat(actualShow).isEqualTo(Show.builder().id(ID).name(SHOW_NAME).build());
	}
	@Test
	void when_getShowById_notFound_should_getNull() {
		given(this.showRepository.findById(ID)).willReturn(Optional.empty());

		final Show actualShow = this.showService.getShowById(ID);

		assertThat(actualShow).isNull();
	}
	@Test
	void when_getPaginatedShows_found_should_getShows() {
		final ShowEntity showEntity = ShowEntity.builder()
				.id(ID)
				.name(SHOW_NAME)
				.build();
		final PageRequest pageable = PageRequest.of(0, 1);
		given(this.showRepository.findAll(pageable)).willReturn(new PageImpl<>(List.of(showEntity),pageable,100 ));

		final Page<Show> actualPage = this.showService.getPaginatedShows(1, 0);

		final Show expectedShow = Show.builder().id(ID).name(SHOW_NAME).build();
		assertThat(actualPage).isEqualTo(new PageImpl<>(List.of(expectedShow), pageable, 100));
	}

}