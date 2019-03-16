package server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import server.entity.StationEntity;

@Repository
public interface StationRepository extends CrudRepository<StationEntity, Long> {

}
