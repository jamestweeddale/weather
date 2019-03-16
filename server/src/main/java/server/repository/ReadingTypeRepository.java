package server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import server.entity.ReadingTypeEntity;

@Repository
public interface ReadingTypeRepository extends CrudRepository<ReadingTypeEntity, Long> {

}
