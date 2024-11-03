package com.daniarques.realnaut_spaceships.repository;

import com.daniarques.realnaut_spaceships.repository.entity.SpaceshipEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceshipRepository extends CrudRepository<SpaceshipEntity, Long>, JpaSpecificationExecutor<SpaceshipEntity> {

}
