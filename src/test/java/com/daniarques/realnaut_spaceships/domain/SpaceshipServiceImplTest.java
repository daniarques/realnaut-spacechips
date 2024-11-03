package com.daniarques.realnaut_spaceships.domain;

import com.daniarques.realnaut_spaceships.domain.mapper.SpaceshipMapperImpl;
import com.daniarques.realnaut_spaceships.domain.model.Spaceship;
import com.daniarques.realnaut_spaceships.repository.ShowRepository;
import com.daniarques.realnaut_spaceships.repository.SpaceshipRepository;
import com.daniarques.realnaut_spaceships.repository.entity.ShowEntity;
import com.daniarques.realnaut_spaceships.repository.entity.SpaceshipEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class SpaceshipServiceImplTest {
    private static final long ID = 1L;
    private static final long SHOW_ID = 2L;
    private static final String SPACESHIP_NAME = "Spaceship name";
    private static final String SHOW_NAME = "Show name";
    @Mock
    private SpaceshipRepository spaceshipRepository;

    @Mock
    private ShowRepository showRepository;

    @Spy
    private SpaceshipMapperImpl spaceshipMapper;

    @InjectMocks
    private SpaceshipServiceImpl spaceshipService;


    @Test
    void when_getSpaceshipById_spaceshipFound_should_returnSpaceship() {

        final SpaceshipEntity spaceshipEntity = SpaceshipEntity.builder()
                .id(ID)
                .name(SPACESHIP_NAME)
                .show(ShowEntity.builder()
                        .id(SHOW_ID)
                        .name(SHOW_NAME)
                        .build())
                .build();
        given(this.spaceshipRepository.findById(ID)).willReturn(Optional.of(spaceshipEntity));

        final Spaceship actualSpaceship = this.spaceshipService.getSpaceshipById(ID);

        final Spaceship expectedSpaceship = Spaceship.builder()
                .id(ID)
                .name(SPACESHIP_NAME)
                .show(SHOW_NAME)
                .build();
        assertThat(actualSpaceship).isEqualTo(expectedSpaceship);
    }

    @Test
    void when_getSpaceshipById_spaceshipNotFound_should_returnNull() {

        given(this.spaceshipRepository.findById(ID)).willReturn(Optional.empty());

        final Spaceship actualSpaceship = this.spaceshipService.getSpaceshipById(ID);

        assertThat(actualSpaceship).isNull();
    }

    @Test
    void when_createSpaceship_showFound_should_createSpaceship() {

        final ShowEntity showEntity = ShowEntity.builder()
                .id(SHOW_ID)
                .name(SHOW_NAME)
                .build();
        given(this.showRepository.findByName(SHOW_NAME)).willReturn(Optional.of(showEntity));

        final Spaceship spaceship = Spaceship.builder()
                .id(ID)
                .name(SPACESHIP_NAME)
                .show(SHOW_NAME)
                .build();
        this.spaceshipService.createSpaceship(spaceship);

        final SpaceshipEntity expectedSpaceshipEntity = SpaceshipEntity.builder()
                .id(ID)
                .name(SPACESHIP_NAME)
                .show(showEntity)
                .build();
        then(this.spaceshipRepository).should().save(expectedSpaceshipEntity);
    }

    @Test
    void when_createSpaceship_showNotFound_should_createSpaceship() {

        given(this.showRepository.findByName(SHOW_NAME)).willReturn(Optional.empty());

        final Spaceship spaceship = Spaceship.builder()
                .id(ID)
                .name(SPACESHIP_NAME)
                .show(SHOW_NAME)
                .build();
        this.spaceshipService.createSpaceship(spaceship);

        final SpaceshipEntity expectedSpaceshipEntity = SpaceshipEntity.builder()
                .id(ID)
                .name(SPACESHIP_NAME)
                .show(ShowEntity.builder()
                        .name(SHOW_NAME)
                        .build())
                .build();
        then(this.spaceshipRepository).should().save(expectedSpaceshipEntity);
    }

    @Test
    void when_updateSpaceship_showFound_should_createSpaceship() {

        given(spaceshipRepository.findById(ID)).willReturn(Optional.of(SpaceshipEntity.builder().build()));
        final ShowEntity showEntity = ShowEntity.builder()
                .id(SHOW_ID)
                .name(SHOW_NAME)
                .build();
        given(this.showRepository.findByName(SHOW_NAME)).willReturn(Optional.of(showEntity));

        final Spaceship spaceship = Spaceship.builder()
                .id(ID)
                .name(SPACESHIP_NAME)
                .show(SHOW_NAME)
                .build();
        this.spaceshipService.updateSpaceship(ID, spaceship);

        final SpaceshipEntity expectedSpaceshipEntity = SpaceshipEntity.builder()
                .id(ID)
                .name(SPACESHIP_NAME)
                .show(showEntity)
                .build();
        then(this.spaceshipRepository).should().save(expectedSpaceshipEntity);
    }

    @Test
    void when_updateSpaceship_showNotFound_should_createSpaceship() {

        given(spaceshipRepository.findById(ID)).willReturn(Optional.of(SpaceshipEntity.builder().build()));
        given(this.showRepository.findByName(SHOW_NAME)).willReturn(Optional.empty());

        final Spaceship spaceship = Spaceship.builder()
                .id(ID)
                .name(SPACESHIP_NAME)
                .show(SHOW_NAME)
                .build();
        this.spaceshipService.updateSpaceship(ID, spaceship);

        final SpaceshipEntity expectedSpaceshipEntity = SpaceshipEntity.builder()
                .id(ID)
                .name(SPACESHIP_NAME)
                .show(ShowEntity.builder()
                        .name(SHOW_NAME)
                        .build())
                .build();
        then(this.spaceshipRepository).should().save(expectedSpaceshipEntity);
    }

    @Test
    void when_updateSpaceship_spaceshipNotFound_should_doNothing() {

        given(this.spaceshipRepository.findById(ID)).willReturn(Optional.empty());

        final Spaceship spaceship = Spaceship.builder()
                .id(ID)
                .name(SPACESHIP_NAME)
                .show(SHOW_NAME)
                .build();
        this.spaceshipService.updateSpaceship(ID, spaceship);

        then(this.showRepository).should(never()).findByName(anyString());
        then(this.spaceshipRepository).should(never()).save(any());
    }

    @Test
    void when_updateSpaceship_spaceshipAndIdDiffer_should_doNothing() {

        final Spaceship spaceship = Spaceship.builder()
                .id(ID)
                .name(SPACESHIP_NAME)
                .show(SHOW_NAME)
                .build();
        this.spaceshipService.updateSpaceship(100L, spaceship);

        then(this.spaceshipRepository).should(never()).findById(anyLong());
        then(this.showRepository).should(never()).findByName(anyString());
        then(this.spaceshipRepository).should(never()).save(any());
    }
    @Test
    void when_deleteSpaceship_should_deleteSpaceship() {

        this.spaceshipService.deleteSpaceship(ID);

        then(this.spaceshipRepository).should().deleteById(ID);
    }
}