package server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import server.entity.Station;

@Repository
public interface StationRepository extends CrudRepository<Station, Long> {

}
