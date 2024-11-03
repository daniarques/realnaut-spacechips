package com.daniarques.realnaut_spaceships.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "spaceship")
public class SpaceshipEntity {

    @Id
    long id;

    String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "show_id", nullable = false)
    ShowEntity show;

}
