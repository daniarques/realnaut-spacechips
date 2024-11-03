package com.daniarques.realnaut_spaceships.controller;

import com.daniarques.realnaut_spaceships.domain.ShowServiceImpl;
import com.daniarques.realnaut_spaceships.domain.model.Show;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ShowControllerIT {
	private static final long ID = 1L;
	private static final String SHOW_NAME = "Show name";
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ShowServiceImpl showService;

	@Test
	void when_getShow_and_showFound_should_succeed() throws Exception {

		final Show show = Show.builder().id(ID).name(SHOW_NAME).build();
		given(this.showService.getShowById(ID)).willReturn(show);

		this.mockMvc.perform(get("/shows/1"))
                .andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value(SHOW_NAME));
	}

	@Test
	void when_getShow_and_showNotFound_should_succeed() throws Exception {

		given(this.showService.getShowById(ID)).willReturn(null);

		this.mockMvc.perform(get("/shows/1"))
                .andExpect(status().isOk())
				.andExpect(jsonPath("$").doesNotExist());
	}
	@Test
	void when_getPaginatedShows_and_showsFound_should_succeed() throws Exception {

		final Show show = Show.builder().id(ID).name(SHOW_NAME).build();
		given(this.showService.getPaginatedShows(1,0))
				.willReturn(new PageImpl<>(List.of(show)));

		this.mockMvc.perform(get("/shows?page=0&size=1"))
                .andExpect(status().isOk())
				.andExpect(jsonPath("$.content[0].id").value("1"))
                .andExpect(jsonPath("$.content[0].name").value(SHOW_NAME))
				.andExpect(jsonPath("$.totalElements").value("1"));
	}
}