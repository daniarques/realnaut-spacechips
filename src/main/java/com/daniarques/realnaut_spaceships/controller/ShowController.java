package com.daniarques.realnaut_spaceships.controller;

import com.daniarques.realnaut_spaceships.domain.ShowService;
import com.daniarques.realnaut_spaceships.domain.model.Show;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get show by ID",
            description = "Get a show (movie or tv show) by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the show"),
            @ApiResponse(responseCode = "404", description = "Show not found")
    })
    @GetMapping(value = "/shows/{id}")
    public ResponseEntity<Show> getShowById(
            @Parameter(description = "ID of the show to retrieve") @PathVariable final Long id) {

        return ResponseEntity.ok(this.showService.getShowById(id));
    }

    @Operation(summary = "Get paginated shows",
            description = "Get a list of shows (movie or tv show) paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping(value = "/shows")
    public ResponseEntity<Page<Show>> getPaginatedShows(
            @Parameter(description = "Page size") @RequestParam final Integer size,
            @Parameter(description = "Page number") @RequestParam final Integer page) {

        return ResponseEntity.ok(this.showService.getPaginatedShows(size, page));
    }

}
