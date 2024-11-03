package com.daniarques.realnaut_spaceships.domain.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class ShowPage {

    List<Show> shows;

    Integer page;

    Integer size;

    Integer totalElements;

}
