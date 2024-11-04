package com.daniarques.realnaut_spaceships.domain.model;

import lombok.*;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Spaceship {

    long id;

    String name;

    String show;
}
