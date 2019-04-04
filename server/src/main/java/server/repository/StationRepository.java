package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entity.StationEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StationRepository extends JpaRepository<StationEntity, Long> {

    Optional<StationEntity> findByUuid(UUID uuid);
}
