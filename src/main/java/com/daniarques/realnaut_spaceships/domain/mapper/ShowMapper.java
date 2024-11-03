package com.daniarques.realnaut_spaceships.domain.mapper;
import com.daniarques.realnaut_spaceships.domain.model.Show;
import com.daniarques.realnaut_spaceships.repository.entity.ShowEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ShowMapper {

	Show map(ShowEntity entity);

}
