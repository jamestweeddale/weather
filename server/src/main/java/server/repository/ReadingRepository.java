package server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import server.entity.ReadingEntity;

@Repository
public interface ReadingRepository extends CrudRepository<ReadingEntity, Long> {

}
