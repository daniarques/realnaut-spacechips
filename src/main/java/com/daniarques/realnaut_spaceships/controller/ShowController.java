package com.daniarques.realnaut_spaceships.controller;

import com.daniarques.realnaut_spaceships.domain.ShowServiceImpl;
import com.daniarques.realnaut_spaceships.domain.model.Show;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ShowController {

	private final ShowServiceImpl showService;

	@GetMapping(value = "/shows/{id}")
	public ResponseEntity<Show> getShowById(@PathVariable Long id) {

		return ResponseEntity.ok(this.showService.getShowById(id));
	}

}
