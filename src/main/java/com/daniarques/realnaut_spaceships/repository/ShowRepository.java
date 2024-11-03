package com.daniarques.realnaut_spaceships.repository;

import com.daniarques.realnaut_spaceships.repository.entity.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, Long>, PagingAndSortingRepository<ShowEntity, Long> {

    Optional<ShowEntity> findByName(String name);
}
