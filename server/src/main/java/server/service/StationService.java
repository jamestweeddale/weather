package server.service;

import server.entity.StationEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface StationService {

    StationEntity save(StationEntity station);

    List<StationEntity> getAll();

    Optional<StationEntity> getById(Long id);

    Optional<StationEntity> getByUUID(UUID uuid);
}
