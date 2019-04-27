package server.service;

import server.entity.ReadingKeyEntity;
import server.entity.ReadingValueEntity;

import java.util.List;

public interface ReadingValueService {

    List<ReadingValueEntity> saveAll(Iterable<ReadingValueEntity> readingEntities);

    ReadingValueEntity getLatestFor(Long stationId, Long readingKeyId);

    List<ReadingKeyEntity> getDistinctReadingKeysForStation(Long stationId);

    ReadingValueEntity getLastForStation(Long stationId);

}
