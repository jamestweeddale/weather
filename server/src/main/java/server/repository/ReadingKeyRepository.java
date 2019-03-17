package server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import server.entity.ReadingKeyEntity;

import java.util.Optional;

@Repository
public interface ReadingKeyRepository extends CrudRepository<ReadingKeyEntity, Long> {

    Optional<ReadingKeyEntity> findOneByName(String name);
}
