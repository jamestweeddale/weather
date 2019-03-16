package server.service;

import server.entity.StationEntity;

import java.util.Optional;


public interface StationService {

    StationEntity save(StationEntity station);

    Iterable<StationEntity> getAll();

    Optional<StationEntity> getById(Long id);
}
