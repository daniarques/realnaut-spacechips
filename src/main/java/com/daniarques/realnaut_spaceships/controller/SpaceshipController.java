package com.daniarques.realnaut_spaceships.controller;

import com.daniarques.realnaut_spaceships.domain.SpaceshipService;
import com.daniarques.realnaut_spaceships.domain.model.Spaceship;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SpaceshipController {

	private final SpaceshipService showService;

	@GetMapping(value = "/spaceship/{id}")
	public ResponseEntity<Spaceship> getSpaceshipById(@PathVariable final Long id) {

		return ResponseEntity.ok(this.showService.getSpaceshipById(id));
	}

	@PostMapping(value = "/spaceship")
	public ResponseEntity<Void> createSpaceship(@RequestBody final Spaceship spaceship) {

		this.showService.createSpaceship(spaceship);

		return ResponseEntity.ok().build();
	}

	@PutMapping(value = "/spaceship/{id}")
	public ResponseEntity<Void> updateSpaceship(@PathVariable Long id, @RequestBody final Spaceship spaceship) {

		this.showService.updateSpaceship(id, spaceship);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/spaceship/{id}")
	public ResponseEntity<Void> deleteSpaceship(@PathVariable Long id) {

		this.showService.deleteSpaceship(id);

		return ResponseEntity.ok().build();
	}

}
