package server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import server.entity.StationEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StationRepository extends CrudRepository<StationEntity, Long> {

    Optional<StationEntity> findByUuid(UUID uuid);
}
