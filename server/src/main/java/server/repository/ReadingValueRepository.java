package server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import server.entity.ReadingValueEntity;

@Repository
public interface ReadingValueRepository extends CrudRepository<ReadingValueEntity, Long> {

}
