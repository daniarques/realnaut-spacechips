package com.daniarques.realnaut_spaceships.domain;

import com.daniarques.realnaut_spaceships.domain.exception.InvalidParameterException;
import com.daniarques.realnaut_spaceships.domain.exception.NotFoundException;
import com.daniarques.realnaut_spaceships.domain.mapper.SpaceshipMapper;
import com.daniarques.realnaut_spaceships.domain.model.Spaceship;
import com.daniarques.realnaut_spaceships.repository.ShowRepository;
import com.daniarques.realnaut_spaceships.repository.SpaceshipRepository;
import com.daniarques.realnaut_spaceships.repository.entity.ShowEntity;
import com.daniarques.realnaut_spaceships.repository.entity.SpaceshipEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static io.micrometer.common.util.StringUtils.isEmpty;
import static java.lang.String.format;

@Component
@AllArgsConstructor
public class SpaceshipServiceImpl implements SpaceshipService {

    private final SpaceshipRepository spaceshipRepository;
    private final ShowRepository showRepository;
    private final SpaceshipMapper spaceshipMapper;

    @Override
    public Spaceship getSpaceshipById(final Long id) {

        final Optional<SpaceshipEntity> entityFound = this.spaceshipRepository.findById(id);
        return entityFound.map(this.spaceshipMapper::map)
                .orElseThrow(() -> new NotFoundException(format("spaceship entity with id:%s not found", id)));
    }

    @Override
    public Page<Spaceship> findAllByFilter(final Integer size, final Integer page, final String nameFilter) {

        final PageRequest pageable = PageRequest.of(page, size);
        final Page<SpaceshipEntity> foundSpaceshipsPage = this.spaceshipRepository.findAll(this.buildSpecification(nameFilter), pageable);

        final List<Spaceship> spaceships = foundSpaceshipsPage.stream()
                .map(this.spaceshipMapper::map)
                .toList();
        return new PageImpl<>(spaceships, pageable, foundSpaceshipsPage.getTotalElements());
    }

    private Specification<SpaceshipEntity> buildSpecification(final String nameFilter) {

        if (isEmpty(nameFilter)) {
            return null;
        }

        return (root, query, builder) -> builder.like(builder.lower(root.get("name")), "%" + nameFilter.toLowerCase() + "%");
    }

    @Override
    public void createSpaceship(final Spaceship spaceship) {

        this.saveSpaceship(spaceship);
    }

    @Override
    public void updateSpaceship(final Long id, final Spaceship spaceship) {

        if (!id.equals(spaceship.getId())) {
            throw new InvalidParameterException("id can not be modified");
        }

        this.spaceshipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("spaceship entity with id:%s not found", id)));

        this.saveSpaceship(spaceship);
    }

    @Override
    public void deleteSpaceship(final Long id) {

        this.spaceshipRepository.deleteById(id);

    }

    private void saveSpaceship(final Spaceship spaceship //TODO 3/11/24: Provide show refactor
    ) {

        final ShowEntity show = this.showRepository.findByName(spaceship.getShow())
                .orElseGet(() -> ShowEntity.builder()
                        .name(spaceship.getShow())
                        .build());

        final SpaceshipEntity spaceshipToSave = this.spaceshipMapper.map(spaceship)
                .toBuilder()
                .show(show)
                .build();
        this.spaceshipRepository.save(spaceshipToSave);
    }
}
