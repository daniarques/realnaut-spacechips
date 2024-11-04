package com.daniarques.realnaut_spaceships.controller;

import com.daniarques.realnaut_spaceships.domain.SpaceshipService;
import com.daniarques.realnaut_spaceships.domain.model.Spaceship;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SpaceshipController {

    private final SpaceshipService spaceshipService;

    @Operation(summary = "Get spaceship by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the spaceship"),
            @ApiResponse(responseCode = "404", description = "Spaceship not found")
    })
    @GetMapping(value = "/spaceship/{id}")
    public ResponseEntity<Spaceship> getSpaceshipById(
            @Parameter(description = "ID of the spaceship to retrieve") @PathVariable final Long id) {

        return ResponseEntity.ok(this.spaceshipService.getSpaceshipById(id));
    }

    @Operation(summary = "Get paginated spaceships")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping(value = "/spaceship")
    public ResponseEntity<Page<Spaceship>> findPageByNameContains(
            @Parameter(description = "Page size") @RequestParam final Integer size,
            @Parameter(description = "Page number") @RequestParam final Integer page,
            @Parameter(description = "Name filter checking all spaceships containing the input") @RequestParam(required = false) final String nameFilter) {

        return ResponseEntity.ok(this.spaceshipService.findAllByFilter(size, page, nameFilter));
    }

    @Operation(summary = "Create spaceship",
            description = "Creates a spaceship. If the 'show' did not exist before, one will be created as well")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the spaceship")
    })
    @PostMapping(value = "/spaceship")
    public ResponseEntity<Void> createSpaceship(
            @Parameter(description = "Spaceship object to be created") @RequestBody final Spaceship spaceship) {

        this.spaceshipService.createSpaceship(spaceship);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update spaceship",
            description = "Updates a spaceship. If the 'show' did not exist before, one will be created as well")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the spaceship")
    })
    @PutMapping(value = "/spaceship/{id}")
    public ResponseEntity<Void> updateSpaceship(
            @Parameter(description = "ID of the spaceship to update") @PathVariable Long id,
            @Parameter(description = "Spaceship object to be updated") @RequestBody final Spaceship spaceship) {

        this.spaceshipService.updateSpaceship(id, spaceship);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete spaceship by ID",
            description = "Deletes a spaceship by ID. If the ID did not exist before it is ignored")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the spaceship")
    })
    @DeleteMapping(value = "/spaceship/{id}")
    public ResponseEntity<Void> deleteSpaceship(@Parameter(description = "ID of the spaceship to delete") @PathVariable Long id) {

        this.spaceshipService.deleteSpaceship(id);

        return ResponseEntity.ok().build();
    }

}
