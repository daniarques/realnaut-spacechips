package com.daniarques.realnaut_spaceships.repository;

import com.daniarques.realnaut_spaceships.repository.entity.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, Long> {
}
