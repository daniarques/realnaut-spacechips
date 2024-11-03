package com.daniarques.realnaut_spaceships.controller;

import com.daniarques.realnaut_spaceships.domain.ShowService;
import com.daniarques.realnaut_spaceships.domain.model.Show;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ShowController {

	private final ShowService showService;

	@GetMapping(value = "/shows/{id}")
	public ResponseEntity<Show> getShowById(@PathVariable final Long id) {

		return ResponseEntity.ok(this.showService.getShowById(id));
	}

	//TODO 3/11/24: Handle 400
	@GetMapping(value = "/shows")
	public ResponseEntity<Page<Show>> getPaginatedShows(@RequestParam final Integer size,
			@RequestParam final Integer page) {

		return ResponseEntity.ok(this.showService.getPaginatedShows(size, page));
	}

}
